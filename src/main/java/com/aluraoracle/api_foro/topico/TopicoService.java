package com.aluraoracle.api_foro.topico;

import com.aluraoracle.api_foro.curso.Categoria;
import com.aluraoracle.api_foro.repository.CursoRepository;
import com.aluraoracle.api_foro.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Topico crear(DatosRegistroTopico datos) {
        // Validar si el autor (usuario) existe
        if (!usuarioRepository.existsById(datos.usuario())) {
            throw new TopicoValidationException("usuario", "No existe usuario " + datos.usuario());
        }

        // Validar si el curso existe y obtener el valor de la enumeración
        Categoria categoria = Categoria.valueOf(datos.curso());  // Aquí se convierte el nombre del curso a una categoría

        // Validar si ya existe un tópico con el mismo título y mensaje
        if (topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            throw new TopicoValidationException("topico", "Ya existe un tópico");
        }

        // Obtener el objeto usuario desde la base de datos
        var usuario = usuarioRepository.getReferenceById(datos.usuario());

        // Crear el nuevo tópico con la categoría obtenida de la enumeración
        var topico = new Topico(datos.titulo(), datos.mensaje(), usuario, categoria);

        // Guardar el tópico en la base de datos
        topicoRepository.save(topico);

        return topico;
    }

}
