package com.aluraoracle.api_foro.topico;

import java.time.LocalDateTime;

public record DatosTopicoShow(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechacreacion,
        Estado estado,
        Long autor,
        Long curso
) {

    public DatosTopicoShow(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechacreacion(),
                topico.getEstado(),
                topico.getAutor().getId(),
                topico.getCurso().getId()
        );
    }

}
