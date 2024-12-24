package com.aluraoracle.api_foro.usuario.exceptions;



public class UsuarioValidationException extends RuntimeException {
    private String campo;
    private String mensaje;

    public UsuarioValidationException(String campo, String mensaje) {
        super(mensaje);
        this.campo = campo;
        this.mensaje = mensaje;
    }

    public String getCampo() {
        return campo;
    }

    public String getMensaje() {
        return mensaje;
    }
}