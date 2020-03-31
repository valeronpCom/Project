package com.restApp;

import Exceptions.BadRequestException;
import Exceptions.InternalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorsTotalHandler {

    @ExceptionHandler({BadRequestException.class, InternalException.class})
    public final ResponseEntity<ErrorsPlace> handleException(Exception exception) {

        if (exception instanceof BadRequestException) {
            ErrorsPlace error = new ErrorsPlace(400, HttpStatus.BAD_REQUEST, exception.getMessage());
            return new ResponseEntity<>(error, HttpStatus.OK);
        }

        else if (exception instanceof InternalException) {
            ErrorsPlace error = new ErrorsPlace(500, HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
            return new ResponseEntity<>(error, HttpStatus.OK);
        }
        ErrorsPlace error = new ErrorsPlace(500, HttpStatus.INTERNAL_SERVER_ERROR, "Unable to run");
        return new ResponseEntity<>(error, HttpStatus.OK);
    }

}