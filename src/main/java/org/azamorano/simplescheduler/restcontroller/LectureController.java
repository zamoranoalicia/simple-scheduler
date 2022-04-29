package org.azamorano.simplescheduler.restcontroller;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.azamorano.simplescheduler.domain.Lecture;
import org.azamorano.simplescheduler.domain.Student;
import org.azamorano.simplescheduler.restcontroller.exception.StudentException;
import org.azamorano.simplescheduler.restcontroller.model.request.EnrollmentRequest;
import org.azamorano.simplescheduler.restcontroller.model.request.LectureRequest;
import org.azamorano.simplescheduler.restcontroller.model.request.StudentRequest;
import org.azamorano.simplescheduler.restcontroller.model.response.ErrorResponse;
import org.azamorano.simplescheduler.service.EnrollmentService;
import org.azamorano.simplescheduler.service.LectureService;
import org.azamorano.simplescheduler.service.StudentService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/lectures")
public class LectureController {

    private final LectureService lectureService;

    @GetMapping(value = "/{lectureCode}")
    public Lecture getStudent(@PathVariable("lectureCode") String lectureCode) {
        return lectureService.getLectureByLectureCode(lectureCode);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void saveLecture(@RequestBody LectureRequest lectureRequest) {
        lectureService.saveLecture(lectureRequest);
    }

    @GetMapping
    public List<Lecture> getAll(@RequestParam Map<String, String> params) {
        return lectureService.getAllLectures(params);
    }
}