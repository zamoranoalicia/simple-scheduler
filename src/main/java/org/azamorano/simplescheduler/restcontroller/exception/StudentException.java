package org.azamorano.simplescheduler.restcontroller.exception;

import org.springframework.http.HttpStatus;

import static org.azamorano.simplescheduler.restcontroller.exception.ErrorCode.USER_EXCEPTION;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class StudentException extends ErrorCodeException {

    private static final long serialVersionUID = -6065144881270405956L;

    public StudentException(String message) {
        super(message, USER_EXCEPTION, INTERNAL_SERVER_ERROR);
    }

    public StudentException(Exception exception) {
        super(exception.getMessage(), USER_EXCEPTION, INTERNAL_SERVER_ERROR);
    }

    public StudentException(Exception exception, HttpStatus status) {
        super(exception.getMessage(), USER_EXCEPTION, status);
    }

    public StudentException(String message, HttpStatus status) {
        super(message, USER_EXCEPTION, status);
    }

    public HttpStatus getHttpStatusCode() {
        return super.httpStatusCode;
    }
}
