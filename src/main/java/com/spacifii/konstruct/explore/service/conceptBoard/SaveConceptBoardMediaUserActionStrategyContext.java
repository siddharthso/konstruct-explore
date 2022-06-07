package com.spacifii.konstruct.explore.service.conceptBoard;

import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardMediaUserAction;
import com.spacifii.konstruct.explore.entities.explore.MediaUserActionType;
import com.spacifii.konstruct.explore.model.dto.explore.MediaUserActionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class SaveConceptBoardMediaUserActionStrategyContext {


    @Autowired
    ConceptBoardMediaUserActionService conceptBoardMediaUserActionService;

    @Autowired
    @Qualifier("SaveConceptBoardMediaUserActionLikeStrategy")
    SaveConceptBoardMediaUserActionStrategy saveConceptBoardMediaUserLikeStrategy;


    @Autowired
    @Qualifier("SaveConceptBoardMediaUserActionQuestionStrategy")
    SaveConceptBoardMediaUserActionStrategy saveConceptBoardMediaUserActionQuestionStrategy;


    @Autowired
    @Qualifier("SaveConceptBoardMediaUserActionReviewStrategy")
    SaveConceptBoardMediaUserActionStrategy saveConceptBoardMediaUserActionReviewStrategy;


    @Autowired
    @Qualifier("SaveConceptBoardMediaUserActionShareStrategy")
    SaveConceptBoardMediaUserActionStrategy saveConceptBoardMediaUserActionShareStrategy;


    @Autowired
    @Qualifier("SaveConceptBoardMediaUserActionViewStrategy")
    SaveConceptBoardMediaUserActionStrategy saveConceptBoardMediaUserActionViewStrategy;

    @Autowired
    @Qualifier("SaveConceptBoardMediaUserActionCommentStrategy")
    SaveConceptBoardMediaUserActionStrategy saveConceptBoardMediaUserActionCommentStrategy;


    private static Map<MediaUserActionType,SaveConceptBoardMediaUserActionStrategy> map = new HashMap<>();

    @PostConstruct
    public void fillMap(){

        map.put(MediaUserActionType.LIKE,saveConceptBoardMediaUserLikeStrategy);
        map.put(MediaUserActionType.QUESTION,saveConceptBoardMediaUserActionQuestionStrategy);
        map.put(MediaUserActionType.REVIEW,saveConceptBoardMediaUserActionReviewStrategy);
        map.put(MediaUserActionType.SHARE,saveConceptBoardMediaUserActionShareStrategy);
        map.put(MediaUserActionType.VIEW,saveConceptBoardMediaUserActionViewStrategy);
        map.put(MediaUserActionType.COMMENT,saveConceptBoardMediaUserActionCommentStrategy);
    }



    public ConceptBoardMediaUserAction save(MediaUserActionDTO mediaUserActionDTO, Long subjectId){
        return map.get(mediaUserActionDTO.getMediaUserActionType()).save(mediaUserActionDTO,subjectId);
    }

    public ConceptBoardMediaUserAction update(MediaUserActionDTO mediaUserActionDTO, Long subjectId){
        ConceptBoardMediaUserAction conceptBoardMediaUserAction = conceptBoardMediaUserActionService.findById(mediaUserActionDTO.getId());
        return map.get(conceptBoardMediaUserAction.getMediaUserActionType()).update(conceptBoardMediaUserAction,mediaUserActionDTO,subjectId);
    }

    public Boolean delete(MediaUserActionDTO mediaUserActionDTO, Long subjectId){
        ConceptBoardMediaUserAction  conceptBoardMediaUserAction = conceptBoardMediaUserActionService.findById(mediaUserActionDTO.getId());
        return map.get(conceptBoardMediaUserAction.getMediaUserActionType()).delete(conceptBoardMediaUserAction,subjectId);
    }

    }
