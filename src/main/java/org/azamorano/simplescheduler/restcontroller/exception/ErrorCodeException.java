package org.azamorano.simplescheduler.restcontroller.exception;

public class ErrorCodeException extends RuntimeException {
    private static final long serialVersionUID = -6065144881270405956L;

    private final ErrorCode errorCode;

    public ErrorCodeException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
