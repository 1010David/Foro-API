package com.aluraoracle.api_foro.perfil;

import jakarta.validation.constraints.NotBlank;

public class PerfilNotFoundException extends RuntimeException {
    public PerfilNotFoundException(@NotBlank String message) {
        super(message);
    }
}