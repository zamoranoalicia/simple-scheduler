package org.azamorano.simplescheduler.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.azamorano.simplescheduler.domain.Enrollment;
import org.azamorano.simplescheduler.domain.Lecture;
import org.azamorano.simplescheduler.domain.Student;
import org.azamorano.simplescheduler.repository.StudentRepository;
import org.azamorano.simplescheduler.restcontroller.exception.StudentException;
import org.azamorano.simplescheduler.restcontroller.model.request.EnrollmentRequest;
import org.azamorano.simplescheduler.restcontroller.model.request.StudentRequest;
import org.azamorano.simplescheduler.restcontroller.model.response.StudentEnrollmentResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.azamorano.simplescheduler.util.ConstantUtil.BAD_ARGUMENT_MESSAGE;
import static org.azamorano.simplescheduler.util.ConstantUtil.ID;
import static org.azamorano.simplescheduler.util.ConstantUtil.INVALID_PARAMS;
import static org.azamorano.simplescheduler.util.ConstantUtil.NOT_FOUND_MESSAGE;
import static org.azamorano.simplescheduler.util.ConstantUtil.NULL_VALUE;
import static org.azamorano.simplescheduler.util.ConstantUtil.STUDENT;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@Service
@AllArgsConstructor
public class StudentService extends BasicService<Student> {

    private final StudentRepository studentRepository;
    private final EnrollmentService enrollmentService;

    public Student getStudentById(String id) {
        if (id == null) {
            throw new StudentException(String.format(BAD_ARGUMENT_MESSAGE, ID, NULL_VALUE),
                    BAD_REQUEST);
        }
        Student foundStudent = studentRepository.get(id);
        if (foundStudent == null) {
            throw new StudentException(String.format(NOT_FOUND_MESSAGE, STUDENT, ID, id), NOT_FOUND);
        }

        return studentRepository.get(id);
    }

    public void save(StudentRequest studentRequest) {
        if (studentRequest == null) {
            throw new StudentException(new IllegalArgumentException(String.format(BAD_ARGUMENT_MESSAGE, STUDENT,
                    NULL_VALUE)), BAD_REQUEST);
        }
        try {
            Student student = Student.builder()
                    .firstName(studentRequest.getFirstName())
                    .lastName(studentRequest.getLastName())
                    .build();
            studentRepository.save(student);

        } catch (Exception exception) {
            log.error("Error when saving student: {}", studentRequest);
            throw new StudentException(exception);
        }

    }

    public List<Student> getAllStudents(Map<String, String> params) {
        List<Student> students;
        try {
            if (params == null || params.isEmpty()) {
                return studentRepository.getAll();
            }
            validateFilterParameters(params, Student.class);
            students = new ArrayList<>(studentRepository.filterBy(params));
        } catch (Exception exception) {
            log.error("Error when retrieving students");
            throw new StudentException(exception);
        }
        return students;
    }

    public void enroll(String id, EnrollmentRequest enrollmentRequest) {
        Student student = studentRepository.get(id);
        if (student == null) {
            throw new StudentException(String.format(INVALID_PARAMS, ID), BAD_REQUEST);
        }
        enrollmentService.enrollStudent(student, enrollmentRequest);
    }

    public StudentEnrollmentResponse getAllEnrollmentsByStudent(String id, Map<String, String> params) {
        Student student = studentRepository.get(id);
        if (student == null) {
            throw new StudentException(String.format(INVALID_PARAMS, ID), BAD_REQUEST);
        }
        Enrollment enrollment = enrollmentService.getEnrollmentsByStudentId(id, params);
        Set<Lecture> lectures = new HashSet<>();
        if (enrollment != null) {
            lectures = enrollment.getLectures();
        }
        return StudentEnrollmentResponse
                .builder()
                .student(student)
                .lectureSet(lectures)
                .build();
    }

    public Student updateStudent(String id, StudentRequest studentRequest) {
        Student student = studentRepository.get(id);
        if (student == null) {
            throw new StudentException(String.format(INVALID_PARAMS, ID), BAD_REQUEST);
        }
        return studentRepository.update(Student.builder()
                .studentId(id)
                .firstName(studentRequest.getFirstName())
                .lastName(studentRequest.getLastName())
                .build());
    }

    public void deleteStudent(String id) {
        Student student = studentRepository.get(id);
        if (student == null) {
            throw new StudentException(String.format(INVALID_PARAMS, ID), BAD_REQUEST);
        }
        studentRepository.remove(student);
    }
}
