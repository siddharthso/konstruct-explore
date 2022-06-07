package com.spacifii.konstruct.explore.service.explore.impl;

import com.spacifii.konstruct.explore.entities.explore.MediaCloudVIsionResponseMapping;
import com.spacifii.konstruct.explore.repository.explore.MediaCloudVIsionResponseMappingRepository;
import com.spacifii.konstruct.explore.service.explore.MediaCloudVIsionResponseMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is service class for MediaCloudVIsionResponseMapping
 */
@Service
public class MediaCloudVIsionResponseMappingServiceImpl implements MediaCloudVIsionResponseMappingService {

    @Autowired
    MediaCloudVIsionResponseMappingRepository mediaCloudVIsionResponseMappingRepository;

    /**
     * This service method is used to save MediaCloudVIsionResponseMapping
     *
     * @param mediaCloudVIsionResponseMapping
     * @return
     */
    @Override
    public MediaCloudVIsionResponseMapping save(MediaCloudVIsionResponseMapping mediaCloudVIsionResponseMapping) {
        return mediaCloudVIsionResponseMappingRepository.save(mediaCloudVIsionResponseMapping);
    }
}
