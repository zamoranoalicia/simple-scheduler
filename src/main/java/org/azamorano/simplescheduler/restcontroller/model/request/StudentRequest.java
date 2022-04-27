package org.azamorano.simplescheduler.restcontroller.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;


@Value
@Builder
@AllArgsConstructor
public class StudentRequest {
    @NotNull
    String firstName;
    @NotNull
    String lastName;
}


