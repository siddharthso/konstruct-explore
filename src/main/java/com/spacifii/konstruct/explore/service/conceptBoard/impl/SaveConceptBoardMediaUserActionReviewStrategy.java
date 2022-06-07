package com.spacifii.konstruct.explore.service.conceptBoard.impl;

import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardMedia;
import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardMediaUserAction;
import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardMediaUserActionStatistics;
import com.spacifii.konstruct.explore.entities.explore.MediaUserActionStatistics;
import com.spacifii.konstruct.explore.model.dto.explore.MediaUserActionDTO;
import com.spacifii.konstruct.explore.repository.conceptBoard.ConceptBoardMediaRepository;
import com.spacifii.konstruct.explore.repository.conceptBoard.ConceptBoardMediaUserActionRepository;
import com.spacifii.konstruct.explore.service.conceptBoard.ConceptBoardMediaService;
import com.spacifii.konstruct.explore.service.conceptBoard.ConceptBoardMediaUserActionService;
import com.spacifii.konstruct.explore.service.conceptBoard.ConceptBoardMediaUserActionStatisticsService;
import com.spacifii.konstruct.explore.service.conceptBoard.SaveConceptBoardMediaUserActionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service("SaveConceptBoardMediaUserActionReviewStrategy")
public class SaveConceptBoardMediaUserActionReviewStrategy implements SaveConceptBoardMediaUserActionStrategy {
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
        ConceptBoardMediaUserActionStatistics mediaUserActionStatistics = conceptBoardMedia.getConceptBoardMediaUserActionStatistics();
        if(mediaUserActionDTO.getRating() != null){
            mediaUserActionStatistics.setTotalRatings(mediaUserActionStatistics.getTotalRatings().add(mediaUserActionDTO.getRating()));
        }
        BigDecimal averageRatings = mediaUserActionStatistics.getTotalRatings()
                .divide(BigDecimal.valueOf(mediaUserActionStatistics.getRatingsCount()).add(BigDecimal.valueOf(1L)));
        mediaUserActionStatistics.setAverageRatings(averageRatings);
        mediaUserActionStatistics.setRatingsCount(mediaUserActionStatistics.getRatingsCount()+1L);
        mediaUserActionStatistics.setLastReviewDate(System.currentTimeMillis());
        conceptBoardMedia.setConceptBoardMediaUserActionStatistics(mediaUserActionStatistics);
        conceptBoardMediaRepository.save(conceptBoardMedia);
        return conceptBoardMediaUserAction;
    }

    @Override
    public ConceptBoardMediaUserAction update(ConceptBoardMediaUserAction conceptBoardMediaUserAction, MediaUserActionDTO mediaUserActionDTO, Long subjectId) {
        BigDecimal ratingToAdd = BigDecimal.ZERO;
        if(mediaUserActionDTO.getRating() != null) {

            ratingToAdd = mediaUserActionDTO.getRating().subtract(conceptBoardMediaUserAction.getRatings());



            ConceptBoardMediaUserActionStatistics mediaUserActionStatistics = conceptBoardMediaUserActionStatisticsService.findByMediaId(mediaUserActionDTO.getMediaId());
            mediaUserActionStatistics.setTotalRatings(mediaUserActionStatistics.getTotalRatings().add(ratingToAdd));
            BigDecimal averageRatings = mediaUserActionStatistics.getTotalRatings()
                    .divide(BigDecimal.valueOf(mediaUserActionStatistics.getRatingsCount()));
            mediaUserActionStatistics.setAverageRatings(averageRatings);
            conceptBoardMediaUserActionStatisticsService.saveOrUpdate(mediaUserActionStatistics,subjectId);
        }
        conceptBoardMediaUserAction.setRatings(mediaUserActionDTO.getRating());
        conceptBoardMediaUserAction.setDetails(mediaUserActionDTO.getDetails());
        conceptBoardMediaUserAction.preUpdate(subjectId);
        return conceptBoardMediaUserActionService.save(conceptBoardMediaUserAction,subjectId);
    }

    @Override
    public Boolean delete(ConceptBoardMediaUserAction conceptBoardMediaUserAction, Long subjectId) {
        ConceptBoardMedia conceptBoardMedia = conceptBoardMediaService.findById(conceptBoardMediaUserAction.getMediaId());
        ConceptBoardMediaUserActionStatistics mediaUserActionStatistics = conceptBoardMedia.getConceptBoardMediaUserActionStatistics();
        mediaUserActionStatistics.setReviewsCount(mediaUserActionStatistics.getReviewsCount()-1);
        if(conceptBoardMediaUserAction.getRatings() != null) {
            mediaUserActionStatistics.setTotalRatings(mediaUserActionStatistics.getTotalRatings().subtract(conceptBoardMediaUserAction.getRatings()));
        }
        BigDecimal averageRatings = mediaUserActionStatistics.getTotalRatings()
                .divide(BigDecimal.valueOf(mediaUserActionStatistics.getRatingsCount()).subtract(BigDecimal.valueOf(1L)));
        mediaUserActionStatistics.setAverageRatings(averageRatings);
        mediaUserActionStatistics.setRatingsCount(mediaUserActionStatistics.getRatingsCount()-1L);
        conceptBoardMedia.setConceptBoardMediaUserActionStatistics(mediaUserActionStatistics);
        conceptBoardMediaRepository.save(conceptBoardMedia);
        conceptBoardMediaUserActionRepository.deleteById(conceptBoardMediaUserAction.getId());
        return true;
    }
}
