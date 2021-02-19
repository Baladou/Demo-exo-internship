/**
 *
 */
package com.exo.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RessourceNotFoundException extends Exception {

    private static final long serialVersionUID = 871618588278915308L;

    public RessourceNotFoundException(String message) {
        super(message);
    }

}
