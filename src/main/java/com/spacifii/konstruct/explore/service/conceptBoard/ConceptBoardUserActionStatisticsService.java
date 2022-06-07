package com.spacifii.konstruct.explore.service.conceptBoard;

import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardUserActionStatistics;
import com.spacifii.konstruct.explore.entities.explore.MediaUserActionStatistics;

import java.util.Map;
import java.util.Set;

/**
 * This is Service Class for ConceptBoardMediaUserActionStatistics
 */
public interface ConceptBoardUserActionStatisticsService {

    /**
     * This service method saves or Updates ConceptBoardMediaUserActionStatistics
     * @param conceptBoardUserActionStatistics
     * @return
     */
    ConceptBoardUserActionStatistics saveOrUpdate(ConceptBoardUserActionStatistics conceptBoardUserActionStatistics, Long subjectId);

    /**
     * This service method gets ConceptBoardUserActionStatistics
     * @param conceptBoardId
     * @return
     */
    ConceptBoardUserActionStatistics findByConceptBoardId(String conceptBoardId);

    /**
     * This service method is used to get ConceptBoardUserActionStatistics by MediaIds
     * @param conceptBoardIds
     * @return
     */
    Map<String,ConceptBoardUserActionStatistics> findByConceptBoardIds(Set<String> conceptBoardIds);

}
