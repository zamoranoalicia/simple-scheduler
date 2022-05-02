package org.azamorano.simplescheduler.repository;

import org.azamorano.simplescheduler.domain.Lecture;
import org.azamorano.simplescheduler.restcontroller.exception.LectureException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.azamorano.simplescheduler.util.ConstantUtil.DATA_RETRIEVE_ERROR;
import static org.azamorano.simplescheduler.util.ConstantUtil.ID;
import static org.azamorano.simplescheduler.util.ConstantUtil.LECTURE;

@Component
public class LectureRepositoryImpl implements LectureRepository {

    private final SchedulerRepository schedulerRepository;

    public LectureRepositoryImpl() {
        schedulerRepository = SchedulerRepository.getInstance();
    }

    @Override
    public Lecture get(String id) {
        try {
            return schedulerRepository.getLectureList()
                    .stream().filter(lecture -> id.equals(lecture.getLectureCode()))
                    .findAny()
                    .orElse(null);
        } catch (Exception ex) {
            throw new LectureException(String.format(DATA_RETRIEVE_ERROR, LECTURE, ID, id));
        }

    }

    @Override
    public void add(Lecture lecture) {
        schedulerRepository.getLectureList().add(lecture);
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
