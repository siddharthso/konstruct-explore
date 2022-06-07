package com.spacifii.konstruct.explore.service.explore.impl;

import com.spacifii.konstruct.explore.entities.explore.ExploreProject;
import com.spacifii.konstruct.explore.entities.explore.ProjectUserAction;
import com.spacifii.konstruct.explore.entities.explore.ProjectUserActionStatistics;
import com.spacifii.konstruct.explore.model.dto.explore.MediaUserActionDTO;
import com.spacifii.konstruct.explore.repository.explore.ExploreProjectRepository;
import com.spacifii.konstruct.explore.repository.explore.ProjectUserActionRepository;
import com.spacifii.konstruct.explore.service.explore.ExploreProjectService;
import com.spacifii.konstruct.explore.service.explore.ProjectUserActionService;
import com.spacifii.konstruct.explore.service.explore.ProjectUserActionStatisticsService;
import com.spacifii.konstruct.explore.service.explore.SaveProjectUserActionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service("SaveProjectUserActionReviewStrategy")
public class SaveProjectUserActionReviewStrategy implements SaveProjectUserActionStrategy {


    @Autowired
    ProjectUserActionService projectUserActionService;

    @Autowired
    ProjectUserActionStatisticsService projectUserActionStatisticsService;

    @Autowired
    ProjectUserActionRepository projectUserActionRepository;


    @Autowired
    ExploreProjectService exploreProjectService;

    @Autowired
    ExploreProjectRepository exploreProjectRepository;



    @Override
    @Transactional
    public ProjectUserAction save(MediaUserActionDTO mediaUserActionDTO, Long subjectId) {

        //This is always insert
        ProjectUserAction projectUserAction = new ProjectUserAction();
        projectUserAction.convertMediaUserActionDTOToMe(mediaUserActionDTO);
        projectUserAction = projectUserActionService.save(projectUserAction,subjectId);


        //This is insert or update
        ExploreProject exploreProject = exploreProjectService.getExploreProjectById(mediaUserActionDTO.getProjectId());
        ProjectUserActionStatistics projectUserActionStatistics = exploreProject.getProjectUserActionStatistics();
        projectUserActionStatistics.setReviewsCount(projectUserActionStatistics.getReviewsCount()+1);
        if(projectUserAction.getRating() != null) {
            projectUserActionStatistics.setTotalRatings(projectUserActionStatistics.getTotalRatings().add(projectUserAction.getRating()));
        }
        BigDecimal averageRatings = projectUserActionStatistics.getTotalRatings()
                .divide(BigDecimal.valueOf(projectUserActionStatistics.getRatingsCount()).add(BigDecimal.valueOf(1L)));
        projectUserActionStatistics.setAverageRatings(averageRatings);
        projectUserActionStatistics.setRatingsCount(projectUserActionStatistics.getRatingsCount()+1L);
        projectUserActionStatistics.setLastReviewDate(System.currentTimeMillis());
        exploreProject.setProjectUserActionStatistics(projectUserActionStatistics);
        exploreProjectRepository.save(exploreProject);
        return projectUserAction;
    }

    @Override
    public ProjectUserAction edit(ProjectUserAction projectUserAction, MediaUserActionDTO mediaUserActionDTO, Long subjectId) {
        BigDecimal ratingToAdd = BigDecimal.ZERO;
        if(mediaUserActionDTO.getRating() != null) {
            ratingToAdd = mediaUserActionDTO.getRating().subtract(projectUserAction.getRating());
            ExploreProject exploreProject = exploreProjectService.getExploreProjectById(mediaUserActionDTO.getProjectId());
            ProjectUserActionStatistics projectUserActionStatistics = exploreProject.getProjectUserActionStatistics();

            projectUserActionStatistics.setTotalRatings(projectUserActionStatistics.getTotalRatings().add(ratingToAdd));
            BigDecimal averageRatings = projectUserActionStatistics.getTotalRatings()
                    .divide(BigDecimal.valueOf(projectUserActionStatistics.getRatingsCount()));
            projectUserActionStatistics.setAverageRatings(averageRatings);
            exploreProject.setProjectUserActionStatistics(projectUserActionStatistics);
            exploreProjectRepository.save(exploreProject);

        }
        projectUserAction.setRating(mediaUserActionDTO.getRating());
        projectUserAction.setDetails(mediaUserActionDTO.getDetails());
        projectUserAction = projectUserActionService.save(projectUserAction,subjectId);
        return projectUserAction;
    }

    @Override
    public Boolean deleteAction(ProjectUserAction projectUserAction, Long subjectId) {
        ExploreProject exploreProject = exploreProjectService.getExploreProjectById(projectUserAction.getProjectId());
        ProjectUserActionStatistics projectUserActionStatistics = exploreProject.getProjectUserActionStatistics();
        projectUserActionStatistics.setReviewsCount(projectUserActionStatistics.getReviewsCount()+1);
        if(projectUserAction.getRating() != null) {
            projectUserActionStatistics.setTotalRatings(projectUserActionStatistics.getTotalRatings().add(projectUserAction.getRating()));
        }
        BigDecimal averageRatings = projectUserActionStatistics.getTotalRatings()
                .divide(BigDecimal.valueOf(projectUserActionStatistics.getRatingsCount()).add(BigDecimal.valueOf(1L)));
        projectUserActionStatistics.setAverageRatings(averageRatings);
        projectUserActionStatistics.setRatingsCount(projectUserActionStatistics.getRatingsCount()+1L);
        exploreProject.setProjectUserActionStatistics(projectUserActionStatistics);
        exploreProjectRepository.save(exploreProject);
        projectUserActionRepository.deleteById(projectUserAction.getId());
        return true;
    }
}
