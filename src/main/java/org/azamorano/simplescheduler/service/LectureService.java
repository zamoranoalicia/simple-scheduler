package org.azamorano.simplescheduler.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.azamorano.simplescheduler.domain.Lecture;
import org.azamorano.simplescheduler.domain.Student;
import org.azamorano.simplescheduler.repository.LectureRepository;
import org.azamorano.simplescheduler.restcontroller.model.request.LectureRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class LectureService {

    private LectureRepository lectureRepository;

    public Lecture getLectureByLectureCode(String lectureCode) {
        return lectureRepository.get(lectureCode);
    }


    public void saveLecture(LectureRequest lectureRequest) {
        lectureRepository.add(Lecture
                .builder()
                .lectureCode(lectureRequest.getLectureCode())
                .lectureDescription(lectureRequest.getDescription())
                .build());
    }

    public List<Lecture> getAllLectures(Map<String, String> params) {
        return lectureRepository.getAll();
    }
}
