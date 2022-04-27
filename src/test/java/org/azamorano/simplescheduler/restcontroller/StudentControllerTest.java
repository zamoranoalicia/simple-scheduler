package org.azamorano.simplescheduler.restcontroller;

import org.azamorano.simplescheduler.restcontroller.model.request.StudentRequest;
import org.azamorano.simplescheduler.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {
    @Mock
    private StudentService studentService;

    private StudentController studentController;

    @BeforeEach
    void setUp() {
        studentController = new StudentController(studentService);
    }

    @Test
    void shouldSaveStudent() {
        studentController.saveStudent(StudentRequest.builder().build());
        verify(studentService).save(any(StudentRequest.class));
    }

    @Test
    void shouldGetStudentById() {
        studentController.getStudent("1234");
        verify(studentService).getStudentById(any(String.class));
    }

    @Test
    void shouldGetAllStudents() {
        studentController.getAll();
        verify(studentService).getAllStudents();
    }
}
