package com.spacifii.konstruct.explore.repository.explore;

import com.spacifii.konstruct.explore.entities.explore.MediaUserActionStatistics;
import com.spacifii.konstruct.explore.entities.explore.ProjectUserActionStatistics;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This is DAO interface for ProjectUserActionStatistics
 */
@Repository
public interface ProjectUserActionStatisticsRepository extends MongoRepository<ProjectUserActionStatistics,String> {


    List<ProjectUserActionStatistics> findByCreatedBy(Long subjectId);


}
