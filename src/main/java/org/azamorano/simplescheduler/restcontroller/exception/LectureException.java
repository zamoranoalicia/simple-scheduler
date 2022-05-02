package org.azamorano.simplescheduler.restcontroller.exception;

import org.springframework.http.HttpStatus;

import static org.azamorano.simplescheduler.restcontroller.exception.ErrorCode.LECTURE_EXCEPTION;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class LectureException extends ErrorCodeException {
    private static final long serialVersionUID = -6065144881270405956L;

    public LectureException(String message,  HttpStatus httpStatusCode) {
        super(message, LECTURE_EXCEPTION, httpStatusCode);
    }

    public LectureException(String message) {
        super(message, LECTURE_EXCEPTION, INTERNAL_SERVER_ERROR);
    }

    public HttpStatus getHttpStatusCode() {
        return super.httpStatusCode;
    }
}
