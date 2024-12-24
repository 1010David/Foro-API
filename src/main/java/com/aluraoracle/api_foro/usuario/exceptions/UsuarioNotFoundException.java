package com.aluraoracle.api_foro.usuario.exceptions;


public class UsuarioNotFoundException extends RuntimeException {
    public UsuarioNotFoundException(String mensaje) {
        super(mensaje);
    }
}
