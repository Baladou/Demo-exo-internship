package com.exo.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT)

public class NothingIsUpdatedException extends Exception {
    private static final long serialVersionUID = 871618588278915308L;

    public NothingIsUpdatedException(String message) {
        super(message);
    }
}



