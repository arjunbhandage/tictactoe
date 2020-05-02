package com.oss.nokia.neo.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE, reason="The game you are trying to play is over")
public class GameOverException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
