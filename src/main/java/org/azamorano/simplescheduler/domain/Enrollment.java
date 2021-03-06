package org.azamorano.simplescheduler.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Value
@Builder(toBuilder = true)
@AllArgsConstructor
public class Enrollment {
    String studentId;
    Set<Lecture> lectures;
}
