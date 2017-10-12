package com.concepttest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ValidationException extends Exception
{

    private static final long serialVersionUID = -2895280583568236652L;


    public ValidationException(String message)
    {
        super(message);
    }

}
