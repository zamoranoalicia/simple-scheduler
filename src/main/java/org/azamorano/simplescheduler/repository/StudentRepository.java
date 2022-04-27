package org.azamorano.simplescheduler.repository;

import org.azamorano.simplescheduler.domain.Student;

import java.util.List;

public interface StudentRepository {
    Student get(String id);

    void add(Student student);

    void update(Student student);

    void remove(Student student);

    List<Student> getAll();
}
