package org.azamorano.simplescheduler.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.azamorano.simplescheduler.domain.Lecture;
import org.azamorano.simplescheduler.domain.Student;
import org.azamorano.simplescheduler.repository.LectureRepository;
import org.azamorano.simplescheduler.restcontroller.exception.LectureException;
import org.azamorano.simplescheduler.restcontroller.model.request.LectureRequest;
import org.azamorano.simplescheduler.restcontroller.model.response.EnrollmentList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.azamorano.simplescheduler.util.ConstantUtil.LECTURE;
import static org.azamorano.simplescheduler.util.ConstantUtil.LECTURE_CODE;
import static org.azamorano.simplescheduler.util.ConstantUtil.NOT_FOUND_MESSAGE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@Service
@AllArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final LectureRegistryService lectureRegistryService;


    public Lecture getLectureByLectureCode(String lectureCode) {
        Lecture lecture = lectureRepository.get(lectureCode);

        if (lecture == null) {
            throw new LectureException(String.format(NOT_FOUND_MESSAGE, LECTURE, LECTURE_CODE, lectureCode), NOT_FOUND);
        }
        return lecture;
    }

    public void saveLecture(LectureRequest lectureRequest) {
        lectureRepository.add(Lecture
                .builder()
                .lectureCode(lectureRequest.getLectureCode())
                .lectureDescription(lectureRequest.getDescription())
                .lectureTitle(lectureRequest.getLectureTitle())
                .build());
    }

    public List<Lecture> getAllLectures(Map<String, String> params) {
        return lectureRepository.getAll();
    }

    public Lecture updateLecture(String lectureCode, LectureRequest lectureRequest) {
        Lecture oldLecture = lectureRepository.get(lectureCode);
        if (oldLecture == null) {
            throw new LectureException(String.format(NOT_FOUND_MESSAGE, LECTURE, LECTURE_CODE, lectureCode), BAD_REQUEST);
        }
        lectureRepository.remove(oldLecture);
        lectureRepository.add(Lecture
                .builder()
                .lectureCode(lectureRequest.getLectureCode())
                .lectureTitle(lectureRequest.getLectureTitle())
                .lectureDescription(lectureRequest.getDescription())
                .build());
        return lectureRepository.get(lectureRequest.getLectureCode());
    }

    public void removeLecture(String lectureCode) {
        Lecture lecture = lectureRepository.get(lectureCode);
        if (lecture == null) {
            throw new LectureException(String.format(NOT_FOUND_MESSAGE, LECTURE, LECTURE_CODE, lectureCode), NOT_FOUND);
        }
        lectureRepository.remove(lecture);
    }

    public EnrollmentList getEnrolledStudentsByLectureCode(String lectureCode) {
        Set<Student> studentList = lectureRegistryService.getStudentRegistryOf(lectureCode);
        return EnrollmentList.builder().studentList(studentList).build();
    }
}
