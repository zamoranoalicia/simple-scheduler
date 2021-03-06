package org.azamorano.simplescheduler.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@AllArgsConstructor
public class Student {

    String studentId;

    String firstName;

    String lastName;
}
