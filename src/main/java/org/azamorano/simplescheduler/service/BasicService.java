package org.azamorano.simplescheduler.service;

import org.azamorano.simplescheduler.restcontroller.exception.StudentException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.azamorano.simplescheduler.util.ConstantUtil.INVALID_PARAMS;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

public abstract class BasicService<T> {
    public void validateFilterParameters(Map<String, String> params, Class clazz) {
        List<Field> fields = Arrays.asList(clazz.getDeclaredFields());
        Set<String> fieldNames = fields.stream().map(field -> field.getName()).collect(Collectors.toSet());
        if (!fieldNames.containsAll(params.keySet())) {
            throw new StudentException(String.format(INVALID_PARAMS, params.keySet().toString()), BAD_REQUEST);
        }
    }
}
