package com.spacifii.konstruct.explore.service.explore.impl;

import com.spacifii.konstruct.explore.entities.explore.Media;
import com.spacifii.konstruct.explore.service.explore.MediaService;
import com.spacifii.konstruct.explore.service.explore.S3UploadDownloadService;
import com.spacifii.konstruct.explore.service.explore.SaveMediaStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("SaveMediaDocumentStrategy")
public class SaveMediaDocumentStrategy implements SaveMediaStrategy {

    @Autowired
    S3UploadDownloadService s3UploadDownloadService;

    @Autowired
    MediaService mediaService;

    @Override
    public Media saveMedia(Media media, MultipartFile multipartFile) {
        media = s3UploadDownloadService.uploadFile(multipartFile, media);
        return mediaService.save(media);
    }
}
