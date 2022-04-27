package org.azamorano.simplescheduler.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Value
@Builder
@AllArgsConstructor
public class Enrollment {

    String lectureCode;

    Integer studentId;

    Date dateOfEnrollment;
}
