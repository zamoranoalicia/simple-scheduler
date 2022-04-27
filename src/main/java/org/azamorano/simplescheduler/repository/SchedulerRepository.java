package org.azamorano.simplescheduler.repository;

import lombok.Value;
import org.azamorano.simplescheduler.domain.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Value
@Component
public class SchedulerRepository {

    private static volatile SchedulerRepository instance = null;
    List<Student> studentList;

    private SchedulerRepository() {
        studentList = new ArrayList<>();
    }

    public static SchedulerRepository getInstance() {
        if (instance == null) {
            synchronized (SchedulerRepository.class) {
                if (instance == null) {
                    instance = new SchedulerRepository();
                }
            }
        }
        return instance;
    }
}
