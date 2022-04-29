package org.azamorano.simplescheduler.repository;

import org.apache.commons.lang3.RandomStringUtils;
import org.azamorano.simplescheduler.domain.Student;
import org.azamorano.simplescheduler.restcontroller.exception.StudentException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.azamorano.simplescheduler.util.ConstantUtil.STUDENT;
import static org.azamorano.simplescheduler.util.ConstantUtil.USER_RETRIEVE_MESSAGE;

@Component
public class StudentRepositoryImpl implements StudentRepository {

    private final int SHORT_ID_LENGTH = 4;
    private final boolean USE_LETTERS = false;
    private final boolean USE_NUMBERS = true;

    private final SchedulerRepository schedulerRepository;

    public StudentRepositoryImpl() {
        schedulerRepository = SchedulerRepository.getInstance();
    }

    @Override
    public Student get(String id) {
        try {

            return schedulerRepository.getStudentList().stream()
                    .filter(student -> id.equals(student.getStudentId()))
                    .findAny()
                    .orElse(null);
        } catch (Exception exception) {
            throw new StudentException(String.format(USER_RETRIEVE_MESSAGE, STUDENT, id));
        }
    }

    @Override
    public void save(Student student) {
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

    @Override
    public Set filterBy(Map<String, String> params) {
        return params.entrySet().stream().map(entry -> filterBy(entry.getKey(), entry.getValue(),
                schedulerRepository.getStudentList()))
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }
}
