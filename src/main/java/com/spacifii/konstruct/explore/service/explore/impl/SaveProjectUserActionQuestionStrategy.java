package com.spacifii.konstruct.explore.service.explore.impl;

import com.spacifii.konstruct.explore.entities.explore.ExploreProject;
import com.spacifii.konstruct.explore.entities.explore.ProjectUserAction;
import com.spacifii.konstruct.explore.entities.explore.ProjectUserActionStatistics;
import com.spacifii.konstruct.explore.model.dto.explore.MediaUserActionDTO;
import com.spacifii.konstruct.explore.repository.explore.ExploreProjectRepository;
import com.spacifii.konstruct.explore.repository.explore.ProjectUserActionRepository;
import com.spacifii.konstruct.explore.service.explore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("SaveProjectUserActionQuestionStrategy")
public class SaveProjectUserActionQuestionStrategy implements SaveProjectUserActionStrategy {


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
        projectUserActionStatistics.setQuestionCount(projectUserActionStatistics.getQuestionCount()+1);
        projectUserActionStatistics.setLastQuestionDate(System.currentTimeMillis());
        exploreProject.setProjectUserActionStatistics(projectUserActionStatistics);
        exploreProjectRepository.save(exploreProject);
        return projectUserAction;
    }

    @Override
    public ProjectUserAction edit(ProjectUserAction projectUserAction, MediaUserActionDTO mediaUserActionDTO, Long subjectId) {
        projectUserAction.setDetails(mediaUserActionDTO.getDetails());
        projectUserAction = projectUserActionService.save(projectUserAction,subjectId);
        return projectUserAction;
    }

    @Override
    public Boolean deleteAction(ProjectUserAction projectUserAction, Long subjectId) {
        ExploreProject exploreProject = exploreProjectService.getExploreProjectById(projectUserAction.getProjectId());
        ProjectUserActionStatistics projectUserActionStatistics = exploreProject.getProjectUserActionStatistics();
        projectUserActionStatistics.setQuestionCount(projectUserActionStatistics.getQuestionCount()-1);
        exploreProject.setProjectUserActionStatistics(projectUserActionStatistics);
        exploreProjectRepository.save(exploreProject);
        projectUserActionRepository.deleteById(projectUserAction.getId());

        return true;
    }
}
