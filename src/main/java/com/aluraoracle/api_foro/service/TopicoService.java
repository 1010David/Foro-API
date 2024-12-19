package com.aluraoracle.api_foro.service;



import com.aluraoracle.api_foro.domain.Topico;
import com.aluraoracle.api_foro.repository.TopicoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    // Listar todos los tópicos
    public List<Topico> obtenerTodos() {
        return topicoRepository.findAll();
    }

    // Obtener un tópico por su ID
    public Topico obtenerPorId(Long id) {
        return topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado con ID: " + id));
    }

    // Crear un nuevo tópico
    public Topico crearTopico(@Valid Topico topico) {
        return topicoRepository.save(topico);
    }

    // Actualizar un tópico existente
    public Topico actualizarTopico(Long id, @Valid Topico topicoActualizado) {
        Topico topico = obtenerPorId(id);
        topico.setTitulo(topicoActualizado.getTitulo());
        topico.setMensaje(topicoActualizado.getMensaje());
        topico.setCurso(topicoActualizado.getCurso());
        topico.setAutor(topicoActualizado.getAutor());

        return topicoRepository.save(topico);
    }

    // Eliminar un tópico
    public void eliminarTopico(Long id) {
        Topico topico = obtenerPorId(id);
        topicoRepository.delete(topico);
    }
}
