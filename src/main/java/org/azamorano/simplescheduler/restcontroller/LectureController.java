package org.azamorano.simplescheduler.restcontroller;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.azamorano.simplescheduler.domain.Lecture;
import org.azamorano.simplescheduler.restcontroller.exception.LectureException;
import org.azamorano.simplescheduler.restcontroller.model.request.LectureRequest;
import org.azamorano.simplescheduler.restcontroller.model.response.EnrollmentList;
import org.azamorano.simplescheduler.restcontroller.model.response.ErrorResponse;
import org.azamorano.simplescheduler.service.EnrollmentService;
import org.azamorano.simplescheduler.service.LectureService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/lectures")
public class LectureController {

    private final LectureService lectureService;

    @GetMapping(value = "/{lectureCode}")
    public Lecture getLecture(@PathVariable("lectureCode") String lectureCode) {
        return lectureService.getLectureByLectureCode(lectureCode);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void saveLecture(@NonNull @Valid @RequestBody LectureRequest lectureRequest) {
        lectureService.saveLecture(lectureRequest);
    }

    @GetMapping
    public List<Lecture> getAll(@RequestParam Map<String, String> params) {
        return lectureService.getAllLectures(params);
    }

    @PutMapping(value = "/{lectureCode}")
    public Lecture updateLecture(@NonNull @PathVariable("lectureCode") String lectureCode,
                                 @NonNull @RequestBody LectureRequest lectureRequest) {
        return lectureService.updateLecture(lectureCode, lectureRequest);
    }


    @DeleteMapping(value = "/{lectureCode}")
    @ResponseStatus(NO_CONTENT)
    public void updateLecture(@NonNull @PathVariable("lectureCode") String lectureCode) {
        lectureService.removeLecture(lectureCode);
    }

    @GetMapping(value = "/{lectureCode}/enrollmentList")
    private EnrollmentList getEnrolledStudents(@NonNull @PathVariable("lectureCode") String lectureCode) {
        return lectureService.getEnrolledStudentsByLectureCode(lectureCode);
    }


    @ExceptionHandler(LectureException.class)
    private ResponseEntity exceptionHandler(LectureException exception) {
        return ResponseEntity
                .status(exception.getHttpStatusCode())
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorResponse(exception.getMessage()));
    }

}