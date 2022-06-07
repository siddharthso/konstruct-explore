package com.spacifii.konstruct.explore.repository.conceptBoard;

import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardMediaUserActionStatistics;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * This is DAO class for ConceptBoardMediaUserActionStatistics
 */
@Repository
public interface ConceptBoardMediaUserActionStatisticsRepository extends MongoRepository<ConceptBoardMediaUserActionStatistics,String> {
}
