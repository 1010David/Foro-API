package com.aluraoracle.api_foro.topico;

import com.aluraoracle.api_foro.curso.Categoria;
import com.aluraoracle.api_foro.topico.record.DatosTopicoShow;
import com.aluraoracle.api_foro.topico.exceptions.TopicoNotFoundException;
import com.aluraoracle.api_foro.topico.exceptions.TopicoValidationException;
import com.aluraoracle.api_foro.curso.CursoRepository;
import com.aluraoracle.api_foro.topico.record.DatosRegistroTopico;
import com.aluraoracle.api_foro.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TopicoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Topico crear(DatosRegistroTopico datos) {
        if (!usuarioRepository.existsById(datos.usuario())) {
            throw new TopicoValidationException("usuario", "No existe usuario " + datos.usuario());
        }

        Categoria categoria = Categoria.valueOf(datos.curso());

        if (topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            throw new TopicoValidationException("topico", "Ya existe un tópico");
        }

        var usuario = usuarioRepository.getReferenceById(datos.usuario());
        var topico = new Topico(datos.titulo(), datos.mensaje(), usuario, categoria);
        topicoRepository.save(topico);

        return topico;
    }

    public List<DatosTopicoShow> obtenerTodos() {
        return topicoRepository.findAll().stream()
                .map(DatosTopicoShow::new)
                .collect(Collectors.toList());
    }

    public Topico editar(Long id, DatosRegistroTopico datosEditar) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new TopicoValidationException("id", "Tópico no encontrado"));

        topico.setTitulo(datosEditar.titulo());
        topico.setMensaje(datosEditar.mensaje());
        topico.setCategoria(Categoria.valueOf(datosEditar.curso()));

        return topicoRepository.save(topico);
    }

    public void eliminar(Long id) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isPresent()) {
            topicoRepository.deleteById(id);
        } else {
            throw new TopicoNotFoundException("Topico with id " + id + " not found");
        }
    }
}