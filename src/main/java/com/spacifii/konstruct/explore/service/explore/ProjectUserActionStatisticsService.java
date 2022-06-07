package com.spacifii.konstruct.explore.service.explore;

import com.spacifii.konstruct.explore.entities.explore.MediaUserActionStatistics;
import com.spacifii.konstruct.explore.entities.explore.ProjectUserActionStatistics;

import java.util.Map;
import java.util.Set;

/**
 * This is Service Class for ProjectUserActionStatistics
 */
public interface ProjectUserActionStatisticsService {

    /**
     * This service method saves or Updates ProjectUserActionStatistics
     * @param projectUserActionStatistics
     * @return
     */
    ProjectUserActionStatistics saveOrUpdate(ProjectUserActionStatistics projectUserActionStatistics, Long subjectId);

    /**
     * This service method gets ProjectUserActionStatistics
     * @param projectId
     * @return
     */
    ProjectUserActionStatistics findByProjectId(String projectId);

    /**
     * This service method is used to get ProjectUserActionStatistics by ProjectIds
     * @param projectIds
     * @return
     */
    Map<String,ProjectUserActionStatistics> findByProjectIds(Set<String> projectIds);

}
