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

import java.math.BigDecimal;

@Service("SaveConceptBoardUserActionReviewStrategy")
public class SaveConceptBoardUserActionReviewStrategy implements SaveConceptBoardUserActionStrategy {
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
        ConceptBoardUserActionStatistics conceptBoardUserActionStatistics = conceptBoard.getConceptBoardUserActionStatistics();conceptBoardUserActionStatistics.setReviewsCount(conceptBoardUserActionStatistics.getReviewsCount()+1);
        if(mediaUserActionDTO.getRating() != null){
            conceptBoardUserActionStatistics.setTotalRatings(conceptBoardUserActionStatistics.getTotalRatings().add(mediaUserActionDTO.getRating()));
        }
        BigDecimal averageRatings = conceptBoardUserActionStatistics.getTotalRatings()
                .divide(BigDecimal.valueOf(conceptBoardUserActionStatistics.getRatingsCount()).add(BigDecimal.valueOf(1L)));
        conceptBoardUserActionStatistics.setAverageRatings(averageRatings);
        conceptBoardUserActionStatistics.setRatingsCount(conceptBoardUserActionStatistics.getRatingsCount()+1L);
        conceptBoard.setConceptBoardUserActionStatistics(conceptBoardUserActionStatistics);
        conceptBoardRepository.save(conceptBoard);
        return conceptBoardUserAction;
    }

    @Override
    public ConceptBoardUserAction update(ConceptBoardUserAction conceptBoardUserAction, MediaUserActionDTO mediaUserActionDTO, Long subjectId) {
        BigDecimal ratingToAdd = BigDecimal.ZERO;
        if(mediaUserActionDTO.getRating() != null) {

            ratingToAdd = mediaUserActionDTO.getRating().subtract(conceptBoardUserAction.getRatings());
            ConceptBoardUserActionStatistics conceptBoardUserActionStatistics = conceptBoardUserActionStatisticsService.findByConceptBoardId(mediaUserActionDTO.getConceptBoardId());
            conceptBoardUserActionStatistics.setTotalRatings(conceptBoardUserActionStatistics.getTotalRatings().add(ratingToAdd));
            BigDecimal averageRatings = conceptBoardUserActionStatistics.getTotalRatings()
                    .divide(BigDecimal.valueOf(conceptBoardUserActionStatistics.getRatingsCount()));
            conceptBoardUserActionStatistics.setAverageRatings(averageRatings);
            conceptBoardUserActionStatisticsService.saveOrUpdate(conceptBoardUserActionStatistics,subjectId);
        }
        conceptBoardUserAction.setRatings(conceptBoardUserAction.getRatings());
        conceptBoardUserAction.setDetails(mediaUserActionDTO.getDetails());
        conceptBoardUserAction.preUpdate(subjectId);
        return conceptBoardUserActionService.save(conceptBoardUserAction,subjectId);
    }

    @Override
    public Boolean delete(ConceptBoardUserAction conceptBoardUserAction, Long subjectId) {
        ConceptBoard conceptBoard = conceptBoardService.findConceptBoardById(conceptBoardUserAction.getConceptBoardId());
        ConceptBoardUserActionStatistics conceptBoardUserActionStatistics = conceptBoard.getConceptBoardUserActionStatistics();
        conceptBoardUserActionStatistics.setReviewsCount(conceptBoardUserActionStatistics.getReviewsCount()-1);
        if(conceptBoardUserAction.getRatings() != null) {
            conceptBoardUserActionStatistics.setTotalRatings(conceptBoardUserActionStatistics.getTotalRatings().subtract(conceptBoardUserAction.getRatings()));
        }
        BigDecimal averageRatings = conceptBoardUserActionStatistics.getTotalRatings()
                .divide(BigDecimal.valueOf(conceptBoardUserActionStatistics.getRatingsCount()).subtract(BigDecimal.valueOf(1L)));
        conceptBoardUserActionStatistics.setAverageRatings(averageRatings);
        conceptBoardUserActionStatistics.setRatingsCount(conceptBoardUserActionStatistics.getRatingsCount()-1L);
        conceptBoard.setConceptBoardUserActionStatistics(conceptBoardUserActionStatistics);
        conceptBoardRepository.save(conceptBoard);
        conceptBoardUserActionRepository.deleteById(conceptBoardUserAction.getId());
        return true;
    }
}
