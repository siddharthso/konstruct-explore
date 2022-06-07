package com.spacifii.konstruct.explore.repository.explore;

import com.spacifii.konstruct.explore.entities.explore.MediaUserActionStatistics;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This is DAO interface for ConceptBoardMediaUserActionStatistics
 */
@Repository
public interface MediaUserActionStatisticsRepository extends MongoRepository<MediaUserActionStatistics,String> {


    List<MediaUserActionStatistics> findByCreatedBy(Long subjectId);


}
