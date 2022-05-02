package org.azamorano.simplescheduler.restcontroller.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
@Builder
@AllArgsConstructor
public class LectureRequest {
    @NotNull
    String lectureCode;

    String description;

    @NotNull
    String lectureTitle;
}
