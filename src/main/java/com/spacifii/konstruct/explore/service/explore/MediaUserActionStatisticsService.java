package com.spacifii.konstruct.explore.service.explore;

import com.spacifii.konstruct.explore.entities.explore.MediaUserActionStatistics;

import java.util.Map;
import java.util.Set;

/**
 * This is Service Class for ConceptBoardMediaUserActionStatistics
 */
public interface MediaUserActionStatisticsService {

    /**
     * This service method saves or Updates ConceptBoardMediaUserActionStatistics
     * @param mediaUserActionStatistics
     * @return
     */
    MediaUserActionStatistics saveOrUpdate(MediaUserActionStatistics mediaUserActionStatistics, Long subjectId);

    /**
     * This service method gets MediaStatistics
     * @param mediaId
     * @return
     */
    MediaUserActionStatistics findByMediaId(String mediaId);

    /**
     * This service method is used to get MediaUserStatistics by MediaIds
     * @param mediaIds
     * @return
     */
    Map<String,MediaUserActionStatistics> findByMediaIds(Set<String> mediaIds);

}
