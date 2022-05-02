package org.azamorano.simplescheduler.restcontroller.exception;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorCode {

    STUDENT_EXCEPTION("userException"),
    ENROLLMENT_EXCEPTION("enrollmentException"),
    LECTURE_EXCEPTION("lectureException"),
    GENERIC_FILTER_EXCEPTION("filteringException");

    private final String code;

    @JsonValue
    public String getCode() {
        return code;
    }
}
