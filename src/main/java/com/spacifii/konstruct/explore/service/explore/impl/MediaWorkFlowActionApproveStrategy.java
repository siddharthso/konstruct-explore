package com.spacifii.konstruct.explore.service.explore.impl;

import com.spacifii.konstruct.explore.entities.explore.Media;
import com.spacifii.konstruct.explore.entities.explore.STATUS;
import com.spacifii.konstruct.explore.repository.explore.MediaRepository;
import com.spacifii.konstruct.explore.service.explore.ExploreMediaElasticService;
import com.spacifii.konstruct.explore.service.explore.MediaService;
import com.spacifii.konstruct.explore.service.explore.MediaWorkFlowActionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service("MediaWorkFlowActionApproveStrategy")
public class MediaWorkFlowActionApproveStrategy implements MediaWorkFlowActionStrategy {


    @Autowired
    MediaService mediaService;

    @Autowired
    ExploreMediaElasticService exploreMediaElasticService;

    @Autowired
    MediaRepository mediaRepository;

    @Override
    @Transactional
    public void doAction(Set<String> mediaIds, Long subjectId) {

        List<Media> medias = mediaService.findByIds(mediaIds);
        for (Media media: medias) {
            media.setStatus(STATUS.APPROVED);
            media.preUpdate(subjectId);
            mediaRepository.save(media);
            exploreMediaElasticService.save(media);
        }


    }
}
