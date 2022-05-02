package org.azamorano.simplescheduler.repository;

import org.azamorano.simplescheduler.domain.LectureRegistry;

public interface LectureRegistryRepository extends BaseRepositoryOperations {
    LectureRegistry get(String lectureCode);

    LectureRegistry save(LectureRegistry build);

    void remove(LectureRegistry lectureRegistry);
}
