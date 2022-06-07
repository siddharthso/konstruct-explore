package com.spacifii.konstruct.explore.service.explore;

import com.spacifii.konstruct.explore.entities.explore.MediaUserActionType;
import com.spacifii.konstruct.explore.entities.explore.ProjectUserAction;
import com.spacifii.konstruct.explore.model.dto.explore.MediaUserActionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class SaveProjectUserActionStrategyContext {


    @Autowired
    ProjectUserActionService projectUserActionService;

    @Autowired
    @Qualifier("SaveProjectUserLikeStrategy")
    SaveProjectUserActionStrategy saveProjectUserLikeStrategy;


    @Autowired
    @Qualifier("SaveProjectUserActionQuestionStrategy")
    SaveProjectUserActionStrategy saveProjectUserActionQuestionStrategy;


    @Autowired
    @Qualifier("SaveProjectUserActionReviewStrategy")
    SaveProjectUserActionStrategy saveProjectUserActionReviewStrategy;


    @Autowired
    @Qualifier("SaveProjectUserActionShareStrategy")
    SaveProjectUserActionStrategy saveProjectUserActionShareStrategy;


    @Autowired
    @Qualifier("SaveProjectUserActionViewStrategy")
    SaveProjectUserActionStrategy saveProjectUserActionViewStrategy;

    @Autowired
    @Qualifier("SaveProjectUserActionCommentStrategy")
    SaveProjectUserActionStrategy saveProjectUserActionCommentStrategy;


    private static Map<MediaUserActionType,SaveProjectUserActionStrategy> map = new HashMap<>();

    @PostConstruct
    public void fillMap(){
        map.put(MediaUserActionType.LIKE,saveProjectUserLikeStrategy);
        map.put(MediaUserActionType.QUESTION,saveProjectUserActionQuestionStrategy);
        map.put(MediaUserActionType.REVIEW,saveProjectUserActionReviewStrategy);
        map.put(MediaUserActionType.SHARE,saveProjectUserActionShareStrategy);
        map.put(MediaUserActionType.VIEW,saveProjectUserActionViewStrategy);
        map.put(MediaUserActionType.COMMENT,saveProjectUserActionCommentStrategy);
    }



    public ProjectUserAction save(MediaUserActionDTO mediaUserActionDTO, Long subjectId){
        return map.get(mediaUserActionDTO.getMediaUserActionType()).save(mediaUserActionDTO,subjectId);
    }


    public ProjectUserAction update(MediaUserActionDTO mediaUserActionDTO, Long subjectId){
        ProjectUserAction projectUserAction = projectUserActionService.findById(mediaUserActionDTO.getId());
        return map.get(projectUserAction.getMediaUserActionType()).edit(projectUserAction,mediaUserActionDTO,subjectId);
    }

    public Boolean delete(MediaUserActionDTO mediaUserActionDTO, Long subjectId){
        ProjectUserAction projectUserAction = projectUserActionService.findById(mediaUserActionDTO.getId());
        return map.get(projectUserAction.getMediaUserActionType()).deleteAction(projectUserAction,subjectId);
    }

    }
