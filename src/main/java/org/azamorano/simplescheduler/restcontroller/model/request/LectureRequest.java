package org.azamorano.simplescheduler.restcontroller.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
@Builder
@AllArgsConstructor
public class LectureRequest {
    @NotNull
    @NotBlank
    String lectureCode;

    String description;

    @NotNull
    @NotBlank
    String lectureTitle;
}
