package com.spacifii.konstruct.explore.repository.conceptBoard;

import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardUserActionStatistics;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * This is DAO class for ConceptBoardUserActionStatistics
 */
@Repository
public interface ConceptBoardUserActionStatisticsRepository extends MongoRepository<ConceptBoardUserActionStatistics,String> {
}
