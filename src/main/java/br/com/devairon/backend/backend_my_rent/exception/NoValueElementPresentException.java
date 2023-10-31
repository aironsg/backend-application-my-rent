package br.com.devairon.backend.backend_my_rent.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoValueElementPresentException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public NoValueElementPresentException(String message) {
        super(message);
    }
}
