package com.spacifii.konstruct.explore.service.explore.impl;

import com.spacifii.konstruct.explore.entities.explore.Media;
import com.spacifii.konstruct.explore.entities.explore.MediaUserAction;
import com.spacifii.konstruct.explore.entities.explore.MediaUserActionStatistics;
import com.spacifii.konstruct.explore.model.dto.explore.MediaUserActionDTO;
import com.spacifii.konstruct.explore.repository.explore.MediaRepository;
import com.spacifii.konstruct.explore.repository.explore.MediaUserActionRepository;
import com.spacifii.konstruct.explore.service.explore.MediaService;
import com.spacifii.konstruct.explore.service.explore.MediaUserActionService;
import com.spacifii.konstruct.explore.service.explore.MediaUserActionStatisticsService;
import com.spacifii.konstruct.explore.service.explore.SaveMediaUserActionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service("SaveMediaUserActionReviewStrategy")
public class SaveMediaUserActionReviewStrategy implements SaveMediaUserActionStrategy {
    @Autowired
    MediaUserActionService mediaUserActionService;

    @Autowired
    MediaUserActionStatisticsService mediaUserActionStatisticsService;

    @Autowired
    MediaUserActionRepository mediaUserActionRepository;

    @Autowired
    MediaService mediaService;

    @Autowired
    MediaRepository mediaRepository;


    @Override
    public MediaUserAction save(MediaUserActionDTO mediaUserActionDTO, Long subjectId) {

        //This is always insert
        MediaUserAction mediaUserAction = new MediaUserAction();
        mediaUserAction.convertMediaUserActionDTOToMe(mediaUserActionDTO);
        mediaUserAction = mediaUserActionService.save(mediaUserAction,subjectId);


        //This is insert or update
        Media media = mediaService.findById(mediaUserActionDTO.getMediaId());
        MediaUserActionStatistics mediaUserActionStatistics = media.getMediaUserActionStatistics();
        mediaUserActionStatistics.setReviewsCount(mediaUserActionStatistics.getReviewsCount()+1);
        if(mediaUserAction.getRatings() != null) {
            mediaUserActionStatistics.setTotalRatings(mediaUserActionStatistics.getTotalRatings().add(mediaUserAction.getRatings()));
        }
        BigDecimal averageRatings = mediaUserActionStatistics.getTotalRatings()
                .divide(BigDecimal.valueOf(mediaUserActionStatistics.getRatingsCount()).add(BigDecimal.valueOf(1L)));
        mediaUserActionStatistics.setAverageRatings(averageRatings);
        mediaUserActionStatistics.setRatingsCount(mediaUserActionStatistics.getRatingsCount()+1L);
        mediaUserActionStatistics.setLastReviewDate(System.currentTimeMillis());
        media.setMediaUserActionStatistics(mediaUserActionStatistics);
        mediaRepository.save(media);
        return mediaUserAction;
    }

    @Override
    public MediaUserAction edit(MediaUserAction mediaUserAction, MediaUserActionDTO mediaUserActionDTO, Long subjectId) {
        BigDecimal ratingToAdd = BigDecimal.ZERO;
        if(mediaUserActionDTO.getRating() != null) {

                ratingToAdd = mediaUserActionDTO.getRating().subtract(mediaUserAction.getRatings());



            Media media = mediaService.findById(mediaUserActionDTO.getMediaId());
            MediaUserActionStatistics mediaUserActionStatistics = media.getMediaUserActionStatistics();
            mediaUserActionStatistics.setTotalRatings(mediaUserActionStatistics.getTotalRatings().add(ratingToAdd));
            BigDecimal averageRatings = mediaUserActionStatistics.getTotalRatings()
                    .divide(BigDecimal.valueOf(mediaUserActionStatistics.getRatingsCount()));
            mediaUserActionStatistics.setAverageRatings(averageRatings);
            media.setMediaUserActionStatistics(mediaUserActionStatistics);
            mediaRepository.save(media);
        }
        mediaUserAction.setRatings(mediaUserActionDTO.getRating());
        mediaUserAction.setDetails(mediaUserActionDTO.getDetails());
        mediaUserActionService.save(mediaUserAction,subjectId);
        return mediaUserAction;
    }

    @Override
    public Boolean deleteAction(MediaUserAction mediaUserAction, Long subjectId) {
        Media media = mediaService.findById(mediaUserAction.getMediaId());
        MediaUserActionStatistics mediaUserActionStatistics = media.getMediaUserActionStatistics();
        mediaUserActionStatistics.setReviewsCount(mediaUserActionStatistics.getReviewsCount()-1);
        if(mediaUserAction.getRatings() != null) {
            mediaUserActionStatistics.setTotalRatings(mediaUserActionStatistics.getTotalRatings().subtract(mediaUserAction.getRatings()));
        }
        BigDecimal averageRatings = mediaUserActionStatistics.getTotalRatings()
                .divide(BigDecimal.valueOf(mediaUserActionStatistics.getRatingsCount()).subtract(BigDecimal.valueOf(1L)));
        mediaUserActionStatistics.setAverageRatings(averageRatings);
        mediaUserActionStatistics.setRatingsCount(mediaUserActionStatistics.getRatingsCount()-1L);
        media.setMediaUserActionStatistics(mediaUserActionStatistics);
        mediaRepository.save(media);
        mediaUserActionRepository.deleteById(mediaUserAction.getId());
        return true;
    }
}
