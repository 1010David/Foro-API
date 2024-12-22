package com.aluraoracle.api_foro.topico;


public class TopicoValidationException extends RuntimeException {
    private String campo;
    private String mensaje;

    public TopicoValidationException(String campo, String mensaje) {
        super(mensaje);
        this.campo = campo;
        this.mensaje = mensaje;
    }

    // Métodos getter
    public String getCampo() {
        return campo;
    }

    public String getMensaje() {
        return mensaje;
    }

}
