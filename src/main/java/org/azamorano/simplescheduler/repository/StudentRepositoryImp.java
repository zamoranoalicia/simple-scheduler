package org.azamorano.simplescheduler.repository;

import org.apache.commons.lang3.RandomStringUtils;
import org.azamorano.simplescheduler.domain.Student;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentRepositoryImp implements StudentRepository {

    final int SHORT_ID_LENGTH = 4;
    final boolean USE_LETTERS = false;
    final boolean USE_NUMBERS = true;
    SchedulerRepository schedulerRepository;

    public StudentRepositoryImp() {
        schedulerRepository = SchedulerRepository.getInstance();
    }

    @Override
    public Student get(String id) {
        return schedulerRepository.getStudentList().stream()
                .filter(student -> id.equals(student.getStudentId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void add(Student student) {
        student = student.toBuilder()
                .studentId(RandomStringUtils.random(SHORT_ID_LENGTH, USE_LETTERS, USE_NUMBERS))
                .build();
        schedulerRepository.getStudentList().add(student);
    }

    @Override
    public void update(Student student) {
        if (student == null) {
            return;
        }

        if (student != null && student.getStudentId() == null) {
            return;
        }

        Student toBeRemoved = schedulerRepository.getStudentList().stream()
                .filter(std -> student.getStudentId().equals(std.getStudentId()))
                .findFirst()
                .orElse(null);
        schedulerRepository.getStudentList().remove(toBeRemoved);
        schedulerRepository.getStudentList().add(student);
    }

    @Override
    public void remove(Student student) {
        schedulerRepository.getStudentList().remove(student);
    }

    @Override
    public List<Student> getAll() {
        return schedulerRepository.getStudentList();
    }
}
