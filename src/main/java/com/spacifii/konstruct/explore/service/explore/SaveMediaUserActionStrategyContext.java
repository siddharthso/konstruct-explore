package com.spacifii.konstruct.explore.service.explore;

import com.spacifii.konstruct.explore.entities.explore.MediaUserAction;
import com.spacifii.konstruct.explore.entities.explore.MediaUserActionType;
import com.spacifii.konstruct.explore.model.dto.explore.MediaUserActionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class SaveMediaUserActionStrategyContext {


    @Autowired
    MediaUserActionService mediaUserActionService;



    @Autowired
    @Qualifier("SaveMediaUserLikeStrategy")
    SaveMediaUserActionStrategy saveMediaUserLikeStrategy;


    @Autowired
    @Qualifier("SaveMediaUserActionQuestionStrategy")
    SaveMediaUserActionStrategy saveMediaUserActionQuestionStrategy;


    @Autowired
    @Qualifier("SaveMediaUserActionReviewStrategy")
    SaveMediaUserActionStrategy saveMediaUserActionReviewStrategy;


    @Autowired
    @Qualifier("SaveMediaUserActionShareStrategy")
    SaveMediaUserActionStrategy saveMediaUserActionShareStrategy;


    @Autowired
    @Qualifier("SaveMediaUserActionViewStrategy")
    SaveMediaUserActionStrategy saveMediaUserActionViewStrategy;

    @Autowired
    @Qualifier("SaveMediaUserActionCommentStrategy")
    SaveMediaUserActionStrategy saveMediaUserActionCommentStrategy;


    private static Map<MediaUserActionType,SaveMediaUserActionStrategy> map = new HashMap<>();

    @PostConstruct
    public void fillMap(){

        map.put(MediaUserActionType.LIKE,saveMediaUserLikeStrategy);
        map.put(MediaUserActionType.QUESTION,saveMediaUserActionQuestionStrategy);
        map.put(MediaUserActionType.REVIEW,saveMediaUserActionReviewStrategy);
        map.put(MediaUserActionType.SHARE,saveMediaUserActionShareStrategy);
        map.put(MediaUserActionType.VIEW,saveMediaUserActionViewStrategy);
        map.put(MediaUserActionType.COMMENT,saveMediaUserActionCommentStrategy);
    }



    public MediaUserAction save(MediaUserActionDTO mediaUserActionDTO, Long subjectId){
        return map.get(mediaUserActionDTO.getMediaUserActionType()).save(mediaUserActionDTO,subjectId);
    }

    public MediaUserAction update(MediaUserActionDTO mediaUserActionDTO, Long subjectId){
        MediaUserAction mediaUserAction = mediaUserActionService.findById(mediaUserActionDTO.getId());
        return map.get(mediaUserAction.getMediaUserActionType()).edit(mediaUserAction,mediaUserActionDTO,subjectId);
    }

    public Boolean delete(MediaUserActionDTO mediaUserActionDTO, Long subjectId){
        MediaUserAction mediaUserAction = mediaUserActionService.findById(mediaUserActionDTO.getId());
        return map.get(mediaUserAction.getMediaUserActionType()).deleteAction(mediaUserAction,subjectId);
    }

    }
