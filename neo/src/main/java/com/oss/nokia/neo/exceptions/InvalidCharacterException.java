package com.oss.nokia.neo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE, reason="Given character is invalid!")
public class InvalidCharacterException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
