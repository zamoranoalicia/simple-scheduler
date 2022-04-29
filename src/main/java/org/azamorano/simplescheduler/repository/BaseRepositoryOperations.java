package org.azamorano.simplescheduler.repository;

import org.azamorano.simplescheduler.restcontroller.exception.StudentException;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public interface BaseRepositoryOperations {

    default Set<Object> filterBy(String key, String value, List objectList) {
        Set<Object> students = new HashSet<>();
        objectList.stream().forEach(student -> {
            try {
                Field filteredField = student.getClass().getDeclaredField(key);
                filteredField.setAccessible(true);
                Object fieldValue = filteredField.get(student);
                Class fieldType = filteredField.getType();

                if (fieldType.cast(fieldValue).equals(value)) {
                    students.add(student);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
                throw new StudentException(String.format("Error when filtering by: %s", key));
            }
        });
        return students;
    }
}
