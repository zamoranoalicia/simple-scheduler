package org.azamorano.simplescheduler.repository;

import org.azamorano.simplescheduler.domain.Lecture;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class LectureRepositoryImpl implements LectureRepository {

    private SchedulerRepository schedulerRepository;

    public LectureRepositoryImpl() {
        schedulerRepository = SchedulerRepository.getInstance();
    }

    @Override
    public Lecture get(String id) {
        return schedulerRepository.getLectureList()
                .stream().filter(lecture -> id.equals(lecture.getLectureCode()))
                .findAny()
                .orElse(null);
    }

    @Override
    public void add(Lecture lecture) {
        schedulerRepository.getLectureList().add(lecture);
    }

    @Override
    public void update(Lecture lecture) {

    }

    @Override
    public void remove(Lecture lecture) {
        schedulerRepository.getLectureList().remove(lecture);
    }

    @Override
    public List<Lecture> getAll() {
        return schedulerRepository.getLectureList();
    }

    @Override
    public Set<Lecture> filterBy(Map<String, String> params) {
        return null;
    }
}
