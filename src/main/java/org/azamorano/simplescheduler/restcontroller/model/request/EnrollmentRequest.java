package org.azamorano.simplescheduler.restcontroller.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class EnrollmentRequest {
    @NonNull
    String lectureCode;
}
