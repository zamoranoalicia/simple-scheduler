package org.azamorano.simplescheduler.restcontroller.exception;

import org.springframework.http.HttpStatus;

import static org.azamorano.simplescheduler.restcontroller.exception.ErrorCode.ENROLLMENT_EXCEPTION;

public class EnrollmentException extends ErrorCodeException {
    public EnrollmentException(String message, HttpStatus httpStatusCode) {
        super(message, ENROLLMENT_EXCEPTION, httpStatusCode);
    }

    public HttpStatus getStatusCode(){
        return this.getStatusCode();
    }
}
