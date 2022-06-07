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
import org.springframework.transaction.annotation.Transactional;

@Service("SaveMediaUserLikeStrategy")
public class SaveMediaUserActionLikeStrategy implements SaveMediaUserActionStrategy {


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
    @Transactional
    public MediaUserAction save(MediaUserActionDTO mediaUserActionDTO, Long subjectId) {

        //This is always insert
        MediaUserAction mediaUserAction = new MediaUserAction();
        mediaUserAction.convertMediaUserActionDTOToMe(mediaUserActionDTO);
        mediaUserAction = mediaUserActionService.save(mediaUserAction,subjectId);



        //This is insert or update
        Media media = mediaService.findById(mediaUserActionDTO.getMediaId());
        MediaUserActionStatistics mediaUserActionStatistics = media.getMediaUserActionStatistics();
        mediaUserActionStatistics.setLikeCount(mediaUserActionStatistics.getLikeCount()+1);
        mediaUserActionStatistics.setLastLikeDate(System.currentTimeMillis());
        media.setMediaUserActionStatistics(mediaUserActionStatistics);
        mediaRepository.save(media);
        return mediaUserAction;
    }

    @Override
    public MediaUserAction edit(MediaUserAction mediaUserAction, MediaUserActionDTO mediaUserActionDTO, Long subjectId) {
        mediaUserAction.setDetails(mediaUserActionDTO.getDetails());
        mediaUserActionService.save(mediaUserAction,subjectId);
        return mediaUserAction;
    }

    @Override
    public Boolean deleteAction(MediaUserAction mediaUserAction, Long subjectId) {
        Media media = mediaService.findById(mediaUserAction.getMediaId());
        MediaUserActionStatistics mediaUserActionStatistics = media.getMediaUserActionStatistics();
        mediaUserActionStatistics.setLikeCount(mediaUserActionStatistics.getLikeCount()-1);
        media.setMediaUserActionStatistics(mediaUserActionStatistics);
        mediaRepository.save(media);
        mediaUserActionRepository.deleteById(mediaUserAction.getId());
        return true;
    }
}
