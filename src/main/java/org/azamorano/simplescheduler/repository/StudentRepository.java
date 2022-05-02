package org.azamorano.simplescheduler.repository;

import org.azamorano.simplescheduler.domain.Student;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface StudentRepository extends BaseRepositoryOperations {
    Student get(String id);

    void save(Student student);

    Student update(Student student);

    void remove(Student student);

    List<Student> getAll();

    Set<Student> filterBy(Map<String, String> params);

    List<Student> getAll(List<String> studentIds);
}
