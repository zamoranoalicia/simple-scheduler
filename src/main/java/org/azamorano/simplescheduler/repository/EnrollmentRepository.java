package org.azamorano.simplescheduler.repository;

import org.azamorano.simplescheduler.domain.Enrollment;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface EnrollmentRepository extends BaseRepositoryOperations {
    Enrollment get(String id);

    void save(Enrollment enrollment);

    void update(Enrollment enrollment);

    void remove(Enrollment enrollment);

    List<Enrollment> getAll();

    Set<Enrollment> filterBy(Map<String, String> params);
}
