package org.azamorano.simplescheduler.repository;

import org.azamorano.simplescheduler.domain.LectureRegistry;
import org.springframework.stereotype.Component;

@Component
public class LectureRegistryRepositoryImpl implements LectureRegistryRepository {

    private final SchedulerRepository schedulerRepository;

    public LectureRegistryRepositoryImpl() {
        this.schedulerRepository = SchedulerRepository.getInstance();
    }

    @Override
    public LectureRegistry get(String lectureCode) {
        return schedulerRepository.getLectureRegistries()
                .stream()
                .filter(lectureRegistry -> lectureCode.equals(lectureRegistry.getLectureCode()))
                .findAny()
                .orElse(null);
    }

    @Override
    public LectureRegistry save(LectureRegistry lectureRegistry) {
        schedulerRepository.getLectureRegistries().add(lectureRegistry);
        return get(lectureRegistry.getLectureCode());
    }

    @Override
    public void remove(LectureRegistry lectureRegistry) {
        schedulerRepository.getLectureRegistries().remove(lectureRegistry);
    }
}
