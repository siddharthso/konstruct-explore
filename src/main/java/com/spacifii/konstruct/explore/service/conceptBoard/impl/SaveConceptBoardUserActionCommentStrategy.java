package com.spacifii.konstruct.explore.service.conceptBoard.impl;

import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoard;
import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardUserAction;
import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardUserActionStatistics;
import com.spacifii.konstruct.explore.model.dto.explore.MediaUserActionDTO;
import com.spacifii.konstruct.explore.repository.conceptBoard.ConceptBoardRepository;
import com.spacifii.konstruct.explore.repository.conceptBoard.ConceptBoardUserActionRepository;
import com.spacifii.konstruct.explore.service.conceptBoard.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SaveConceptBoardUserActionCommentStrategy")
public class SaveConceptBoardUserActionCommentStrategy implements SaveConceptBoardUserActionStrategy {

    @Autowired
    ConceptBoardUserActionService conceptBoardUserActionService;

    @Autowired
    ConceptBoardUserActionStatisticsService conceptBoardUserActionStatisticsService;

    @Autowired
    ConceptBoardUserActionRepository conceptBoardUserActionRepository;

    @Autowired
    ConceptBoardService conceptBoardService;

    @Autowired
    ConceptBoardRepository conceptBoardRepository;




    @Override
    public ConceptBoardUserAction save(MediaUserActionDTO mediaUserActionDTO, Long subjectId) {
        //This is always insert
        ConceptBoardUserAction conceptBoardUserAction = new ConceptBoardUserAction();
        conceptBoardUserAction.convertCOnceUserActionDTOToMe(mediaUserActionDTO);
        conceptBoardUserAction = conceptBoardUserActionService.save(conceptBoardUserAction,subjectId);


        //This is insert or update
        ConceptBoard conceptBoard = conceptBoardService.findConceptBoardById(mediaUserActionDTO.getConceptBoardId());
        ConceptBoardUserActionStatistics conceptBoardUserActionStatistics = conceptBoard.getConceptBoardUserActionStatistics();
        conceptBoardUserActionStatistics.setCommentsCount(conceptBoardUserActionStatistics.getCommentsCount()+1);
        conceptBoard.setConceptBoardUserActionStatistics(conceptBoardUserActionStatistics);
        conceptBoardRepository.save(conceptBoard);
        return conceptBoardUserAction;
    }

    @Override
    public ConceptBoardUserAction update(ConceptBoardUserAction conceptBoardUserAction, MediaUserActionDTO mediaUserActionDTO, Long subjectId) {
        conceptBoardUserAction.setDetails(mediaUserActionDTO.getDetails());
        conceptBoardUserAction.preUpdate(subjectId);
        return conceptBoardUserActionService.save(conceptBoardUserAction,subjectId);
    }

    @Override
    public Boolean delete(ConceptBoardUserAction conceptBoardUserAction, Long subjectId) {
        ConceptBoard conceptBoard = conceptBoardService.findConceptBoardById(conceptBoardUserAction.getConceptBoardId());
        ConceptBoardUserActionStatistics conceptBoardUserActionStatistics = conceptBoard.getConceptBoardUserActionStatistics();
        conceptBoardUserActionStatistics.setCommentsCount(conceptBoardUserActionStatistics.getCommentsCount()-1);
        conceptBoard.setConceptBoardUserActionStatistics(conceptBoardUserActionStatistics);
        conceptBoardRepository.save(conceptBoard);
        conceptBoardUserActionRepository.deleteById(conceptBoardUserAction.getId());
        return true;
    }
}
