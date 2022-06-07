package com.spacifii.konstruct.explore.service.explore.impl;

import com.spacifii.konstruct.explore.entities.explore.MediaUserActionStatistics;
import com.spacifii.konstruct.explore.repository.explore.MediaUserActionStatisticsRepository;
import com.spacifii.konstruct.explore.service.explore.BeanUtils;
import com.spacifii.konstruct.explore.service.explore.MediaService;
import com.spacifii.konstruct.explore.service.explore.MediaUserActionStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * This is Service Class for MediaUserActionStatistics
 */
@Service
public class MediaUserActionStatisticsServiceImpl implements MediaUserActionStatisticsService {


    @Autowired
    MediaService mediaService;

    @Autowired
    MediaUserActionStatisticsRepository mediaUserActionStatisticsRepository;

    /**
     * This service method saves or Updates MediaUserActionStatistics
     * @param mediaUserActionStatistics
     * @return
     */
    @Override
    @Transactional
    public MediaUserActionStatistics saveOrUpdate(MediaUserActionStatistics mediaUserActionStatistics, Long subjectId) {
        if(mediaUserActionStatistics.getCreationDate() == null){
            mediaUserActionStatistics.preSave(subjectId);
        } else {
            mediaUserActionStatistics.preUpdate(subjectId);
        }
        return mediaUserActionStatisticsRepository.save(mediaUserActionStatistics);
    }

    /**
     * This service method gets MediaStatistics
     * @param mediaId
     * @return
     */
    @Override
    public MediaUserActionStatistics findByMediaId(String mediaId) {
        Optional<MediaUserActionStatistics> mediaUserActionStatisticsOptional = mediaUserActionStatisticsRepository.findById(mediaId);
            if (mediaUserActionStatisticsOptional.isPresent()){
            return mediaUserActionStatisticsOptional.get();
        }
        MediaUserActionStatistics mediaUserActionStatistics = new MediaUserActionStatistics();
        mediaUserActionStatistics.setMediaId(mediaId);
        return mediaUserActionStatisticsRepository.save(mediaUserActionStatistics);
    }

    /**
     * This service method is used to get MediaUserStatistics by MediaIds
     * @param mediaIds
     * @return
     */
    @Override
    public Map<String, MediaUserActionStatistics> findByMediaIds(Set<String> mediaIds) {
        return BeanUtils.makeCollection(
                mediaUserActionStatisticsRepository.findAllById(mediaIds)).stream()
                .collect(Collectors.toMap(MediaUserActionStatistics::getMediaId, Function.identity()));
    }
}
