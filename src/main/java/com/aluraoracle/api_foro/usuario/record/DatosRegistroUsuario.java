package com.aluraoracle.api_foro.usuario.record;

import com.aluraoracle.api_foro.perfil.Perfil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DatosRegistroUsuario(
        @NotBlank(message = "El nombre no puede estar vacío")
        @Size(max = 100, message = "El nombre debe tener entre 1 y 100 caracteres")
        String nombre,

        @NotBlank(message = "El correo electrónico no puede estar vacío")
        @Size(max = 100, message = "El correo electrónico debe tener entre 1 y 100 caracteres")
        String correoelectronico,

        @NotBlank(message = "La contraseña no puede estar vacía")
        @Size(max = 100, message = "La contraseña debe tener entre 1 y 100 caracteres")
        String contrasena,

        @NotNull(message = "El perfil no puede estar vacío")
        Perfil perfil
) {}