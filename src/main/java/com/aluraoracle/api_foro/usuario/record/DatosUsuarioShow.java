package com.aluraoracle.api_foro.usuario.record;

import com.aluraoracle.api_foro.usuario.Usuario;

public record DatosUsuarioShow (
        Long id,
        String nombre,
        String correoelectronico
) {
    public DatosUsuarioShow(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getCorreoelectronico());
    }
}