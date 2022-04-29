package org.azamorano.simplescheduler.repository;

import lombok.Value;
import org.azamorano.simplescheduler.domain.Enrollment;
import org.azamorano.simplescheduler.domain.Lecture;
import org.azamorano.simplescheduler.domain.LectureRegistry;
import org.azamorano.simplescheduler.domain.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Value
@Component
public class SchedulerRepository {

    private static volatile SchedulerRepository instance = null;
    List<Student> studentList;
    List<Enrollment> enrollmentList;
    List<LectureRegistry> lectureRegistries;
    List<Lecture> lectureList;


    private SchedulerRepository() {
        studentList = new ArrayList<>();
        enrollmentList =  new ArrayList<>();
        lectureRegistries = new ArrayList<>();
        lectureList = new ArrayList<>();
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
