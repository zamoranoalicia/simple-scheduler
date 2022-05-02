package org.azamorano.simplescheduler.service;

import lombok.AllArgsConstructor;
import org.azamorano.simplescheduler.domain.LectureRegistry;
import org.azamorano.simplescheduler.domain.Student;
import org.azamorano.simplescheduler.repository.LectureRegistryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@AllArgsConstructor
public class LectureRegistryService {
    private final LectureRegistryRepository lectureRegistryRepository;

    public void updateLectureRegistry(Student student, String lectureCode) {
        LectureRegistry lectureRegistry = lectureRegistryRepository.get(lectureCode);
        if (lectureRegistry == null) {
            createNewRecord(student, lectureCode);
        } else {
            addStudentToRecord(student, lectureRegistry);
        }
    }

    private void addStudentToRecord(Student student, LectureRegistry lectureRegistry) {
        Set<Student> students = lectureRegistry.getEnrolledStudents();
        students.add(student);
        LectureRegistry newRegistry = lectureRegistry.toBuilder().enrolledStudents(students).build();
        lectureRegistryRepository.remove(lectureRegistry);
        lectureRegistryRepository.save(newRegistry);

    }

    private void createNewRecord(Student student, String lectureCode) {
        Set<Student> studentSet = new HashSet<>();
        studentSet.add(student);
        lectureRegistryRepository.save(LectureRegistry
                .builder()
                .lectureCode(lectureCode)
                .enrolledStudents(studentSet)
                .build());
    }

    public Set<Student> getStudentRegistryOf(String lectureCode) {
        LectureRegistry lectureRegistry = lectureRegistryRepository.get(lectureCode);

        if (lectureRegistry == null) {
            return new HashSet<>();
        }
        return lectureRegistry.getEnrolledStudents();
    }
}
