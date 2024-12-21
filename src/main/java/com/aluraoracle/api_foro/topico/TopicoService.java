package com.aluraoracle.api_foro.topico;

import com.aluraoracle.api_foro.repository.CursoRepository;
import com.aluraoracle.api_foro.usuario.Usuario;
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
        if (!usuarioRepository.existsById(datos.autor())) {
            throw new TopicoValidationException("autor", "No existe un usuario con el identificador " + datos.autor());
        }

        // Validar si el curso existe
        if (!cursoRepository.existsById(datos.curso())) {
            throw new TopicoValidationException("curso", "No existe un curso con el identificador " + datos.curso());
        }

        // Validar si ya existe un tópico con el mismo título y mensaje
        if (topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            throw new TopicoValidationException("topico", "Ya existe un tópico con igual título y mensaje");
        }

        // Obtener los objetos de usuario y curso desde la base de datos
        var usuario = usuarioRepository.getReferenceById(datos.autor());
        var curso = cursoRepository.getReferenceById(datos.curso());

        // Crear el nuevo tópico
        var topico = new Topico(datos.titulo(), datos.mensaje(), usuario, curso);

        // Guardar el tópico en la base de datos
        topicoRepository.save(topico);

        return topico;
    }

}
