package org.azamorano.simplescheduler.repository;

import org.azamorano.simplescheduler.domain.Enrollment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class EnrollmentRepositoryImpl implements EnrollmentRepository {

    SchedulerRepository schedulerRepository;

    public EnrollmentRepositoryImpl() {
        schedulerRepository = SchedulerRepository.getInstance();
    }

    @Override
    public Enrollment get(String id) {
        return schedulerRepository.getEnrollmentList()
                .stream()
                .filter(enrollment -> id.equals(enrollment.getStudentId()))
                .findAny()
                .orElse(null);
    }

    @Override
    public void save(Enrollment enrollment) {
        schedulerRepository.getEnrollmentList().add(enrollment);
    }

    @Override
    public void update(Enrollment enrollment) {

    }

    @Override
    public void remove(Enrollment enrollment) {
        schedulerRepository.getEnrollmentList().remove(enrollment);
    }

    @Override
    public List<Enrollment> getAll() {
        return schedulerRepository.getEnrollmentList();
    }

    @Override
    public Set<Enrollment> filterBy(Map<String, String> params) {
        return null;
    }
}
