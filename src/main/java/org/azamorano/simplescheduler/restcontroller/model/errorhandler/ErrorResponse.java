package org.azamorano.simplescheduler.restcontroller.model.errorhandler;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorResponse {
    Integer statusCode;
    String message;
}
