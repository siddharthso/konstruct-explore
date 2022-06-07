package com.spacifii.konstruct.explore.service.conceptBoard;

import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardMediaUserActionStatistics;
import com.spacifii.konstruct.explore.entities.explore.MediaUserActionStatistics;

import java.util.Map;
import java.util.Set;

/**
 * This is Service Class for ConceptBoardMediaUserActionStatistics
 */
public interface ConceptBoardMediaUserActionStatisticsService {

    /**
     * This service method saves or Updates ConceptBoardMediaUserActionStatistics
     * @param conceptBoardMediaUserActionStatistics
     * @return
     */
    ConceptBoardMediaUserActionStatistics saveOrUpdate(ConceptBoardMediaUserActionStatistics conceptBoardMediaUserActionStatistics, Long subjectId);

    /**
     * This service method gets MediaStatistics
     * @param mediaId
     * @return
     */
    ConceptBoardMediaUserActionStatistics findByMediaId(String mediaId);

    /**
     * This service method is used to get ConceptBoardMediaUserActionStatistics by MediaIds
     * @param mediaIds
     * @return
     */
    Map<String,ConceptBoardMediaUserActionStatistics> findByMediaIds(Set<String> mediaIds);

}
