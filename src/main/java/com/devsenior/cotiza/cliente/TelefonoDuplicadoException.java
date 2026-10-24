package com.devsenior.cotiza.cliente;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class TelefonoDuplicadoException extends RuntimeException {

    public TelefonoDuplicadoException(String mensaje) {
        super(mensaje);
    }
}
