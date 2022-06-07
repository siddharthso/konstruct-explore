package com.spacifii.konstruct.explore.service.explore.impl;

import com.spacifii.konstruct.explore.entities.explore.Media;
import com.spacifii.konstruct.explore.service.explore.MediaService;
import com.spacifii.konstruct.explore.service.explore.SaveMediaStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("SaveMediaExternalUrlStrategy")
public class SaveMediaExternalUrlStrategy implements SaveMediaStrategy {
   @Autowired
    MediaService mediaService;

    @Override
    public Media saveMedia(Media media, MultipartFile multipartFile) {
        return mediaService.save(media);
    }
}
