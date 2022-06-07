package com.spacifii.konstruct.explore.service.conceptBoard.impl;

import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardMedia;
import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardMediaUserAction;
import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardMediaUserActionStatistics;
import com.spacifii.konstruct.explore.model.dto.explore.MediaUserActionDTO;
import com.spacifii.konstruct.explore.repository.conceptBoard.ConceptBoardMediaRepository;
import com.spacifii.konstruct.explore.repository.conceptBoard.ConceptBoardMediaUserActionRepository;
import com.spacifii.konstruct.explore.repository.conceptBoard.ConceptBoardRepository;
import com.spacifii.konstruct.explore.service.conceptBoard.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SaveConceptBoardMediaUserActionCommentStrategy")
public class SaveConceptBoardMediaUserActionCommentStrategy implements SaveConceptBoardMediaUserActionStrategy {

    @Autowired
    ConceptBoardMediaUserActionService conceptBoardMediaUserActionService;

    @Autowired
    ConceptBoardMediaUserActionStatisticsService conceptBoardMediaUserActionStatisticsService;

    @Autowired
    ConceptBoardMediaUserActionRepository conceptBoardMediaUserActionRepository;

    @Autowired
    ConceptBoardMediaService conceptBoardMediaService;

    @Autowired
    ConceptBoardMediaRepository conceptBoardMediaRepository;


    @Override
    public ConceptBoardMediaUserAction save(MediaUserActionDTO mediaUserActionDTO, Long subjectId) {
        //This is always insert
        ConceptBoardMediaUserAction mediaUserAction = new ConceptBoardMediaUserAction();
        mediaUserAction.convertMediaUserActionDTOToMe(mediaUserActionDTO);
        mediaUserAction = conceptBoardMediaUserActionService.save(mediaUserAction,subjectId);


        //This is insert or update
        ConceptBoardMedia conceptBoardMedia = conceptBoardMediaService.findById(mediaUserActionDTO.getMediaId());
        ConceptBoardMediaUserActionStatistics mediaUserActionStatistics = conceptBoardMedia.getConceptBoardMediaUserActionStatistics();
        mediaUserActionStatistics.setCommentsCount(mediaUserActionStatistics.getCommentsCount()+1);
        mediaUserActionStatistics.setLastCommentDate(System.currentTimeMillis());
        conceptBoardMedia.setConceptBoardMediaUserActionStatistics(mediaUserActionStatistics);
        conceptBoardMediaRepository.save(conceptBoardMedia);
        return mediaUserAction;
    }

    @Override
    public ConceptBoardMediaUserAction update(ConceptBoardMediaUserAction conceptBoardMediaUserAction, MediaUserActionDTO mediaUserActionDTO, Long subjectId) {
        conceptBoardMediaUserAction.setDetails(mediaUserActionDTO.getDetails());
        conceptBoardMediaUserAction.preUpdate(subjectId);
        return conceptBoardMediaUserActionService.save(conceptBoardMediaUserAction,subjectId);

    }

    @Override
    public Boolean delete(ConceptBoardMediaUserAction conceptBoardMediaUserAction, Long subjectId) {
        ConceptBoardMedia conceptBoardMedia = conceptBoardMediaService.findById(conceptBoardMediaUserAction.getMediaId());
        ConceptBoardMediaUserActionStatistics mediaUserActionStatistics = conceptBoardMedia.getConceptBoardMediaUserActionStatistics();
        mediaUserActionStatistics.setCommentsCount(mediaUserActionStatistics.getCommentsCount()-1);
        conceptBoardMedia.setConceptBoardMediaUserActionStatistics(mediaUserActionStatistics);
        conceptBoardMediaRepository.save(conceptBoardMedia);
        conceptBoardMediaUserActionRepository.deleteById(conceptBoardMediaUserAction.getId());
        return true;
    }
}
