package org.azamorano.simplescheduler.repository;

import org.azamorano.simplescheduler.domain.Lecture;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface LectureRepository extends BaseRepositoryOperations {
    Lecture get(String id);

    void add(Lecture lecture);

    void remove(Lecture lecture);

    List<Lecture> getAll();

    Set<Lecture> filterBy(Map<String, String> params);
}
