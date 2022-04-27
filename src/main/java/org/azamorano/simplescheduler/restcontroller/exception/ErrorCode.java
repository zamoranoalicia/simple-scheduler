package org.azamorano.simplescheduler.restcontroller.exception;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorCode {

    USER_EXCEPTION("userException");

    private final String code;

    @JsonValue
    public String getCode() {
        return code;
    }
}
