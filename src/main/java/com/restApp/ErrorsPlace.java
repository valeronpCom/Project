package com.restApp;

import org.springframework.http.HttpStatus;

public class ErrorsPlace {

    private int errorCode;
    private HttpStatus status;
    private String message;

    ErrorsPlace(int errorCode, HttpStatus status, String message) {
        this.errorCode = errorCode;
        this.status = status;
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

}