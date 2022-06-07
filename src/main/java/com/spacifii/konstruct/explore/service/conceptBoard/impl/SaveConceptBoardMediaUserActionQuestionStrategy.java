package com.spacifii.konstruct.explore.service.conceptBoard.impl;

import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardMedia;
import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardMediaUserAction;
import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardMediaUserActionStatistics;
import com.spacifii.konstruct.explore.model.dto.explore.MediaUserActionDTO;
import com.spacifii.konstruct.explore.repository.conceptBoard.ConceptBoardMediaRepository;
import com.spacifii.konstruct.explore.repository.conceptBoard.ConceptBoardMediaUserActionRepository;
import com.spacifii.konstruct.explore.service.conceptBoard.ConceptBoardMediaService;
import com.spacifii.konstruct.explore.service.conceptBoard.ConceptBoardMediaUserActionService;
import com.spacifii.konstruct.explore.service.conceptBoard.ConceptBoardMediaUserActionStatisticsService;
import com.spacifii.konstruct.explore.service.conceptBoard.SaveConceptBoardMediaUserActionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SaveConceptBoardMediaUserActionQuestionStrategy")
public class SaveConceptBoardMediaUserActionQuestionStrategy implements SaveConceptBoardMediaUserActionStrategy {


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
        ConceptBoardMediaUserAction conceptBoardMediaUserAction = new ConceptBoardMediaUserAction();
        conceptBoardMediaUserAction.convertMediaUserActionDTOToMe(mediaUserActionDTO);
        conceptBoardMediaUserAction = conceptBoardMediaUserActionService.save(conceptBoardMediaUserAction,subjectId);


        //This is insert or update
        ConceptBoardMedia conceptBoardMedia = conceptBoardMediaService.findById(mediaUserActionDTO.getMediaId());
        ConceptBoardMediaUserActionStatistics conceptBoardMediaUserActionStatistics = conceptBoardMedia.getConceptBoardMediaUserActionStatistics();
        conceptBoardMediaUserActionStatistics.setQuestionCount(conceptBoardMediaUserActionStatistics.getQuestionCount()+1);
        conceptBoardMediaUserActionStatistics.setLastQuestionDate(System.currentTimeMillis());
        conceptBoardMedia.setConceptBoardMediaUserActionStatistics(conceptBoardMediaUserActionStatistics);
        conceptBoardMediaRepository.save(conceptBoardMedia);
        return conceptBoardMediaUserAction;
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
        ConceptBoardMediaUserActionStatistics conceptBoardMediaUserActionStatistics = conceptBoardMedia.getConceptBoardMediaUserActionStatistics();
        conceptBoardMediaUserActionStatistics.setQuestionCount(conceptBoardMediaUserActionStatistics.getQuestionCount()+1);
        conceptBoardMedia.setConceptBoardMediaUserActionStatistics(conceptBoardMediaUserActionStatistics);
        conceptBoardMediaRepository.save(conceptBoardMedia);
        conceptBoardMediaUserActionRepository.deleteById(conceptBoardMediaUserAction.getId());
        return true;
    }
}
