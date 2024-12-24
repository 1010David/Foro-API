package com.aluraoracle.api_foro.topico.record;

import com.aluraoracle.api_foro.topico.Estado;
import com.aluraoracle.api_foro.topico.Topico;

import java.time.LocalDateTime;

public record DatosTopicoShow(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechacreacion,
        Estado estado,
        Long usuario,
        String curso
) {

    public DatosTopicoShow(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechacreacion(),
                topico.getEstado(),
                topico.getUsuario().getId(),
                topico.getCurso() != null ? topico.getCurso().name() : "Curso no asignado"
        );
    }

}
