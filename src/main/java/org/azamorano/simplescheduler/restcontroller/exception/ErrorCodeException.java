package org.azamorano.simplescheduler.restcontroller.exception;

import org.springframework.http.HttpStatus;

public class ErrorCodeException extends RuntimeException {
    private static final long serialVersionUID = -6065144881270405956L;
    ErrorCode errorCode;
    HttpStatus httpStatusCode;

    public ErrorCodeException(String message, ErrorCode errorCode, HttpStatus httpStatusCode) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatusCode = httpStatusCode;
    }
}
