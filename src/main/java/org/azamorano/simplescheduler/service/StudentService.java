package org.azamorano.simplescheduler.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.azamorano.simplescheduler.domain.Student;
import org.azamorano.simplescheduler.repository.StudentRepository;
import org.azamorano.simplescheduler.restcontroller.exception.StudentException;
import org.azamorano.simplescheduler.restcontroller.model.request.StudentRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Student getStudentById(String id) {
        if (id == null) {
            throw new StudentException(new IllegalArgumentException("Student id should not be null"));
        }
        try {

            return studentRepository.get(id);
        } catch (Exception e) {
            log.error("Error when retrieving Student for id: {}", id);
            throw new StudentException(e);
        }
    }

    public void save(StudentRequest studentRequest) {
        if (studentRequest == null) {
            throw new StudentException(new IllegalArgumentException("Student should not be null"));
        }
        try {
            Student student = Student.builder()
                    .firstName(studentRequest.getFirstName())
                    .lastName(studentRequest.getLastName())
                    .build();
            studentRepository.add(student);

        } catch (Exception exception) {
            log.error("Error when saving student: {}", studentRequest);
            throw new StudentException(exception);
        }

    }

    public List<Student> getAllStudents() {
        try {
            return studentRepository.getAll();
        } catch (Exception exception) {
            log.error("Error when retrieving students");
            throw new StudentException(exception);
        }
    }
}
