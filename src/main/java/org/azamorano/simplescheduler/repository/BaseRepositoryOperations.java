package org.azamorano.simplescheduler.repository;

import org.azamorano.simplescheduler.restcontroller.exception.ErrorCodeException;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.azamorano.simplescheduler.restcontroller.exception.ErrorCode.GENERIC_FILTER_EXCEPTION;
import static org.azamorano.simplescheduler.util.ConstantUtil.FILTERING_ERROR;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Component
public interface BaseRepositoryOperations {

    default Set<Object> filterBy(String key, String value, List objectList) {
        Set<Object> objects = new HashSet<>();
        objectList.stream().forEach(student -> {
            try {
                Field filteredField = student.getClass().getDeclaredField(key);
                filteredField.setAccessible(true);
                Object fieldValue = filteredField.get(student);
                Class fieldType = filteredField.getType();

                if (fieldType.cast(fieldValue).toString().toLowerCase().contains(value.toLowerCase())) {
                    objects.add(student);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
                throw new ErrorCodeException(String.format(FILTERING_ERROR, key, value), GENERIC_FILTER_EXCEPTION,
                        INTERNAL_SERVER_ERROR);
            }
        });
        return objects;
    }
}
