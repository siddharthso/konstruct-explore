package com.spacifii.konstruct.explore.service.explore.impl;

import com.spacifii.konstruct.explore.entities.explore.MediaUserActionStatistics;
import com.spacifii.konstruct.explore.entities.explore.ProjectUserActionStatistics;
import com.spacifii.konstruct.explore.repository.explore.MediaUserActionStatisticsRepository;
import com.spacifii.konstruct.explore.repository.explore.ProjectUserActionStatisticsRepository;
import com.spacifii.konstruct.explore.service.explore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * This is Service Class for ProjectUserActionStatistics
 */
@Service
public class ProjectUserActionStatisticsServiceImpl implements ProjectUserActionStatisticsService {


    @Autowired
    ExploreProjectService exploreProjectService;

    @Autowired
    ProjectUserActionStatisticsRepository projectUserActionStatisticsRepository;

    /**
     * This service method saves or Updates ProjectUserActionStatistics
     * @param projectUserActionStatistics
     * @return
     */
    @Override
    @Transactional
    public ProjectUserActionStatistics saveOrUpdate(ProjectUserActionStatistics projectUserActionStatistics, Long subjectId) {
        if(projectUserActionStatistics.getCreationDate() == null){
            projectUserActionStatistics.preSave(subjectId);
        } else {
            projectUserActionStatistics.preUpdate(subjectId);
        }
        return projectUserActionStatisticsRepository.save(projectUserActionStatistics);
    }

    /**
     * This service method gets MediaStatistics
     * @param projectId
     * @return
     */
    @Override
    public ProjectUserActionStatistics findByProjectId(String projectId) {
        Optional<ProjectUserActionStatistics> projectUserActionStatisticsOptional = projectUserActionStatisticsRepository.findById(projectId);
        if (projectUserActionStatisticsOptional.isPresent()){
            return projectUserActionStatisticsOptional.get();
        }

        ProjectUserActionStatistics projectUserActionStatistics = new ProjectUserActionStatistics();
        projectUserActionStatistics.setProjectId(projectId);
        return projectUserActionStatistics;
    }

    /**
     * This service method is used to get ProjectUserActionStatistics by MediaIds
     * @param projectIds
     * @return
     */
    @Override
    public Map<String, ProjectUserActionStatistics> findByProjectIds(Set<String> projectIds) {
        return BeanUtils.makeCollection(
                projectUserActionStatisticsRepository.findAllById(projectIds)).stream()
                .collect(Collectors.toMap(ProjectUserActionStatistics::getProjectId, Function.identity()));
    }
}
