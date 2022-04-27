package org.azamorano.simplescheduler.restcontroller.mapper;

import org.azamorano.simplescheduler.domain.Student;
import org.azamorano.simplescheduler.restcontroller.model.request.StudentRequest;
import org.mapstruct.Mapper;


@Mapper
public interface DefaultMapper {
    Student mapToModel(StudentRequest request);
}
