package com.restApp.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalException extends Exception {
    private int exceptionCode;

    public InternalException(int exceptionCode, String message) {
        super(message);
        this.exceptionCode = exceptionCode;
    }

    public int getExceptionCode() {
        return exceptionCode;
    }
}