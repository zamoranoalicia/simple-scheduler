package org.azamorano.simplescheduler.service;

import org.azamorano.simplescheduler.domain.Enrollment;
import org.azamorano.simplescheduler.domain.Lecture;
import org.azamorano.simplescheduler.domain.Student;
import org.azamorano.simplescheduler.repository.EnrollmentRepository;
import org.azamorano.simplescheduler.restcontroller.model.request.EnrollmentRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EnrollmentServiceTest {

    @Mock
    private EnrollmentRepository enrollmentRepository;

    @Mock
    private LectureService lectureService;

    @Mock
    private LectureRegistryService lectureRegistryService;

    private EnrollmentService enrollmentService;

    @BeforeEach
    public void setUp() {
        this.enrollmentService = new EnrollmentService(enrollmentRepository, lectureService, lectureRegistryService);
    }

    @Test
    public void shouldEnrollNewStudent() {
        Student student = Student
                .builder()
                .studentId("1234")
                .firstName("John")
                .lastName("Doe")
                .build();
        EnrollmentRequest enrollmentRequest = EnrollmentRequest
                .builder()
                .lectureCode("MAT-001")
                .build();
        Lecture lecture = Lecture.builder().lectureTitle("Alegebra 1").lectureCode("MAT-001").build();
        Enrollment expectedEnrollment = Enrollment
                .builder()
                .studentId(student.getStudentId())
                .lectures(Collections.singleton(lecture))
                .build();
        when(lectureService.getLectureByLectureCode("MAT-001")).thenReturn(lecture);
        enrollmentService.enrollStudent(student, enrollmentRequest);

        verify(lectureService).getLectureByLectureCode(("MAT-001"));
        verify(enrollmentRepository).save(expectedEnrollment);
        verify(lectureRegistryService).updateLectureRegistry(student, "MAT-001");
    }
}
