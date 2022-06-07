package com.spacifii.konstruct.explore.service.conceptBoard;

import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardUserAction;
import com.spacifii.konstruct.explore.entities.explore.MediaUserActionType;
import com.spacifii.konstruct.explore.model.dto.explore.MediaUserActionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class SaveConceptBoardUserActionStrategyContext {


    @Autowired
    ConceptBoardUserActionService conceptBoardUserActionService;


    @Autowired
    @Qualifier("SaveConceptBoardUserActionLikeStrategy")
    SaveConceptBoardUserActionStrategy saveConceptBoardUserLikeStrategy;


    @Autowired
    @Qualifier("SaveConceptBoardUserActionQuestionStrategy")
    SaveConceptBoardUserActionStrategy saveConceptBoardUserActionQuestionStrategy;


    @Autowired
    @Qualifier("SaveConceptBoardUserActionReviewStrategy")
    SaveConceptBoardUserActionStrategy saveConceptBoardUserActionReviewStrategy;


    @Autowired
    @Qualifier("SaveConceptBoardUserActionShareStrategy")
    SaveConceptBoardUserActionStrategy saveConceptBoardUserActionShareStrategy;


    @Autowired
    @Qualifier("SaveConceptBoardUserActionViewStrategy")
    SaveConceptBoardUserActionStrategy saveConceptBoardUserActionViewStrategy;

    @Autowired
    @Qualifier("SaveConceptBoardUserActionCommentStrategy")
    SaveConceptBoardUserActionStrategy saveConceptBoardUserActionCommentStrategy;


    private static Map<MediaUserActionType,SaveConceptBoardUserActionStrategy> map = new HashMap<>();

    @PostConstruct
    public void fillMap(){

        map.put(MediaUserActionType.LIKE,saveConceptBoardUserLikeStrategy);
        map.put(MediaUserActionType.QUESTION,saveConceptBoardUserActionQuestionStrategy);
        map.put(MediaUserActionType.REVIEW,saveConceptBoardUserActionReviewStrategy);
        map.put(MediaUserActionType.SHARE,saveConceptBoardUserActionShareStrategy);
        map.put(MediaUserActionType.VIEW,saveConceptBoardUserActionViewStrategy);
        map.put(MediaUserActionType.COMMENT,saveConceptBoardUserActionCommentStrategy);
    }



    public ConceptBoardUserAction save(MediaUserActionDTO mediaUserActionDTO, Long subjectId){
        return map.get(mediaUserActionDTO.getMediaUserActionType()).save(mediaUserActionDTO,subjectId);
    }


    public ConceptBoardUserAction update(MediaUserActionDTO mediaUserActionDTO, Long subjectId){
        ConceptBoardUserAction conceptBoardUserAction = conceptBoardUserActionService.findById(mediaUserActionDTO.getId());
        return map.get(conceptBoardUserAction.getMediaUserActionType()).update(conceptBoardUserAction,mediaUserActionDTO,subjectId);
    }

    public Boolean delete(MediaUserActionDTO mediaUserActionDTO, Long subjectId){
        ConceptBoardUserAction  conceptBoardUserAction = conceptBoardUserActionService.findById(mediaUserActionDTO.getId());
        return map.get(conceptBoardUserAction.getMediaUserActionType()).delete(conceptBoardUserAction,subjectId);
    }


    }
