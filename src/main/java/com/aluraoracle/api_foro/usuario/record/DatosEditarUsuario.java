package com.aluraoracle.api_foro.usuario.record;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosEditarUsuario(
        @NotBlank String nombre,
        @Email @NotBlank String correoelectronico,
        @NotBlank String contrasena,
        @NotBlank String perfil
) {}