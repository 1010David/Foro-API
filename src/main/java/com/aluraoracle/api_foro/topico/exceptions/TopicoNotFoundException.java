package com.aluraoracle.api_foro.topico.exceptions;



public class TopicoNotFoundException extends RuntimeException {
    public TopicoNotFoundException(String message) {
        super(message);
    }
}