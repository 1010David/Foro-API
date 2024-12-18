package com.aluraoracle.api_foro.topico;

import com.aluraoracle.api_foro.topico.record.DatosActualizarTopico;
import com.aluraoracle.api_foro.topico.record.DatosCrearTopico;
import com.aluraoracle.api_foro.topico.record.DatosVerTopico;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    // Crear un nuevo tópico
    public DatosVerTopico crearTopico(DatosCrearTopico datosCrear) {
        Topico topico = new Topico(
                datosCrear.titulo(),
                datosCrear.mensaje(),
                datosCrear.autorId(),
                datosCrear.cursoId(),
                datosCrear.estado()
        );
        topicoRepository.save(topico);
        return new DatosVerTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getEstado());
    }

    // Actualizar un tópico existente
    public DatosVerTopico actualizarTopico(DatosActualizarTopico datosActualizar) {
        Topico topico = topicoRepository.findById(datosActualizar.id())
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado"));

        if (datosActualizar.titulo() != null) {
            topico.setTitulo(datosActualizar.titulo());
        }
        if (datosActualizar.mensaje() != null) {
            topico.setMensaje(datosActualizar.mensaje());
        }
        if (datosActualizar.estado() != null) {
            topico.setEstado(datosActualizar.estado());
        }

        topicoRepository.save(topico);
        return new TopicoDatosVer(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getEstado());
    }

    // Listar todos los tópicos
    public List<DatosVerTopico> listarTopicos() {
        return topicoRepository.findAll()
                .stream()
                .map(topico -> new DatosVerTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getEstado()))
                .collect(Collectors.toList());
    }

    // Eliminar un tópico por ID
    public void eliminarTopico(Long id) {
        topicoRepository.deleteById(id);
    }
}
