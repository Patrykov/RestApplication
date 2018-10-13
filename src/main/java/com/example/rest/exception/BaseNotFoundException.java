package com.example.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BaseNotFoundException extends RuntimeException {

    public BaseNotFoundException(String exception) {
        super(exception);
    }
}
