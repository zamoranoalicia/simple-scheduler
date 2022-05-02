package org.azamorano.simplescheduler.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.azamorano.simplescheduler.domain.Enrollment;
import org.azamorano.simplescheduler.domain.Lecture;
import org.azamorano.simplescheduler.domain.Student;
import org.azamorano.simplescheduler.repository.EnrollmentRepository;
import org.azamorano.simplescheduler.restcontroller.exception.EnrollmentException;
import org.azamorano.simplescheduler.restcontroller.model.request.EnrollmentRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.azamorano.simplescheduler.util.ConstantUtil.INVALID_PARAMS;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@Service
@AllArgsConstructor
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final LectureService lectureService;
    private final LectureRegistryService lectureRegistryService;

    public void enrollStudent(Student student, EnrollmentRequest enrollmentRequest) {
        Lecture lecture = lectureService.getLectureByLectureCode(enrollmentRequest.getLectureCode());
        if (lecture == null) {
            throw new EnrollmentException(String.format(INVALID_PARAMS, enrollmentRequest.getLectureCode()),
                    BAD_REQUEST);
        }
        try {
            Enrollment existingEnrolment = enrollmentRepository.get(student.getStudentId());

            if (existingEnrolment != null) {
                enrollExistingStudent(lecture, existingEnrolment);
            } else {
                enrollNewStudent(lecture, student);
            }
            lectureRegistryService.updateLectureRegistry(student, enrollmentRequest.getLectureCode());

        } catch (Exception exception) {
            throw new EnrollmentException(exception.getMessage(), INTERNAL_SERVER_ERROR);
        }
    }

    private void enrollExistingStudent(Lecture lecture, Enrollment existingEnrolment) {
        Set<Lecture> lectureSet = new HashSet<>(existingEnrolment.getLectures());
        lectureSet.add(lecture);
        Enrollment updatedEnrollment = existingEnrolment.toBuilder().lectures(lectureSet).build();
        enrollmentRepository.remove(existingEnrolment);
        enrollmentRepository.save(updatedEnrollment);
    }

    private void enrollNewStudent(Lecture lecture, Student student) {
        Set<Lecture> lectureSet = new HashSet<>();
        lectureSet.add(lecture);
        enrollmentRepository.save(Enrollment.builder().studentId(student.getStudentId()).lectures(lectureSet).build());
    }

    public Enrollment getEnrollmentsByStudentId(String id, Map<String, String> params) {
        return enrollmentRepository.get(id);
    }
}
