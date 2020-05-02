
package com.oss.nokia.neo.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Game for given ID does not exist!") 
public class GameDoesNotExist extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
