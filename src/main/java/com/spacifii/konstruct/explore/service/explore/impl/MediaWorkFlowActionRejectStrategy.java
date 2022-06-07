package com.spacifii.konstruct.explore.service.explore.impl;

import com.spacifii.konstruct.explore.entities.explore.Media;
import com.spacifii.konstruct.explore.entities.explore.STATUS;
import com.spacifii.konstruct.explore.repository.explore.MediaRepository;
import com.spacifii.konstruct.explore.service.explore.ExploreMediaElasticService;
import com.spacifii.konstruct.explore.service.explore.MediaService;
import com.spacifii.konstruct.explore.service.explore.MediaWorkFlowActionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service("MediaWorkFlowActionRejectStrategy")
public class MediaWorkFlowActionRejectStrategy implements MediaWorkFlowActionStrategy
{


    @Autowired
    MediaService mediaService;

    @Autowired
    ExploreMediaElasticService exploreMediaElasticService;

    @Autowired
    MediaRepository mediaRepository;

    @Override
    public void doAction(Set<String> mediaIds, Long subjectId) {

        List<Media> medias = mediaService.findByIds(mediaIds);
        for (Media media: medias) {
            media.setStatus(STATUS.RESTRICTED);
            media.preUpdate(subjectId);
            mediaRepository.save(media);
            exploreMediaElasticService.save(media);
        }

    }
}
