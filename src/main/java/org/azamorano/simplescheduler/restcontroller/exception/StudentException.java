package org.azamorano.simplescheduler.restcontroller.exception;

import static org.azamorano.simplescheduler.restcontroller.exception.ErrorCode.USER_EXCEPTION;

public class StudentException extends ErrorCodeException {

    private static final long serialVersionUID = -6065144881270405956L;

    public StudentException(Exception exception) {
        super(exception.getMessage(), USER_EXCEPTION);
    }

    public StudentException(String message) {
        super(message, USER_EXCEPTION);
    }
}
