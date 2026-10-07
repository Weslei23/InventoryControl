package com.wsdev.simplestock.controller;

import com.wsdev.simplestock.common.exceptions.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice
{
    @ExceptionHandler( RecordNotFoundException.class )
    @ResponseStatus( HttpStatus.NOT_FOUND )
    public String handleNotFoundException( RecordNotFoundException exception )
    {
        return exception.getMessage();
    }
}
