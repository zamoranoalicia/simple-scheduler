package org.azamorano.simplescheduler.restcontroller;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.azamorano.simplescheduler.domain.Student;
import org.azamorano.simplescheduler.restcontroller.exception.EnrollmentException;
import org.azamorano.simplescheduler.restcontroller.exception.StudentException;
import org.azamorano.simplescheduler.restcontroller.model.request.EnrollmentRequest;
import org.azamorano.simplescheduler.restcontroller.model.request.StudentRequest;
import org.azamorano.simplescheduler.restcontroller.model.response.ErrorResponse;
import org.azamorano.simplescheduler.restcontroller.model.response.StudentEnrollmentResponse;
import org.azamorano.simplescheduler.service.EnrollmentService;
import org.azamorano.simplescheduler.service.StudentService;
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
@RequestMapping(value = "/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping(value = "/{id}")
    public Student getStudent(@PathVariable("id") String id) {
        return studentService.getStudentById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void saveStudent(@Valid @RequestBody StudentRequest studentRequest) {
        studentService.save(studentRequest);
    }

    @GetMapping
    public List<Student> getAll(@RequestParam Map<String, String> params) {
        return studentService.getAllStudents(params);
    }

    @PostMapping(value = "/{id}/enrollment")
    @ResponseStatus(CREATED)
    public void enrollStudent(@NonNull @PathVariable("id") String id,
                              @NonNull @Valid @RequestBody EnrollmentRequest enrollmentRequest) {
        studentService.enroll(id, enrollmentRequest);
    }

    @PutMapping(value = "/{id}")
    public Student updateStudent(@NonNull @PathVariable("id") String id,
                                 @NonNull @Valid @RequestBody StudentRequest studentRequest) {
        return studentService.updateStudent(id, studentRequest);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteStudent(@NonNull @PathVariable("id") String id) {
        studentService.deleteStudent(id);
    }

    @GetMapping(value = "/{id}/enrollment")
    public StudentEnrollmentResponse getEnrollments(@NonNull @PathVariable("id") String id, @RequestParam Map<String, String> params) {
        return studentService.getAllEnrollmentsByStudent(id, params);
    }

    @ExceptionHandler(StudentException.class)
    private ResponseEntity exceptionHandler(StudentException exception) {
        return ResponseEntity
                .status(exception.getHttpStatusCode())
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorResponse(exception.getMessage()));
    }

    @ExceptionHandler(EnrollmentException.class)
    private ResponseEntity exceptionHandler(EnrollmentException exception) {
        return ResponseEntity
                .status(exception.getStatusCode())
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorResponse(exception.getMessage()));
    }

}
