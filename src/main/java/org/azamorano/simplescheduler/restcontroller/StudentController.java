package org.azamorano.simplescheduler.restcontroller;

import lombok.AllArgsConstructor;
import org.azamorano.simplescheduler.domain.Student;
import org.azamorano.simplescheduler.restcontroller.model.request.StudentRequest;
import org.azamorano.simplescheduler.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;


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
    public void saveStudent(@RequestBody StudentRequest studentRequest) {
        studentService.save(studentRequest);
    }

    @GetMapping
    public List<Student> getAll() {
        return studentService.getAllStudents();
    }
}
