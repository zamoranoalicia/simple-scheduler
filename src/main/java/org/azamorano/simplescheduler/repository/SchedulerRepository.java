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
    List<Lecture> lectureList;
    List<LectureRegistry> lectureRegistries;


    private SchedulerRepository() {
        this.studentList = new ArrayList<>();
        this.enrollmentList =  new ArrayList<>();
        this.lectureList = new ArrayList<>();
        this.lectureRegistries = new ArrayList<>();
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
