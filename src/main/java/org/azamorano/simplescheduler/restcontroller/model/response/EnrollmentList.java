package org.azamorano.simplescheduler.restcontroller.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.azamorano.simplescheduler.domain.Student;

import java.util.Set;

@Value
@Builder
@AllArgsConstructor
public class EnrollmentList {
    Set<Student> studentList;
}
