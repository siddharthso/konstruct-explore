package com.spacifii.konstruct.explore.service.generalMedia;

import com.spacifii.konstruct.explore.entities.generalMedia.GeneralMedia;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * This is service class for GeneralMedia
 */
public interface GeneralMediaService {

    /**
     * This service method is used to upload Media into amazonS3 Bucket
     * @param multipartFiles
     * @param subjectId
     * @return
     */
    List<GeneralMedia> upload(MultipartFile[] multipartFiles, Long subjectId, String key);

    /**
     * This service method is used to upload Media into amazonS3 Bucket and get Google Keywords
     * @param multipartFiles
     * @param subjectId
     * @return
     */
    List<GeneralMedia> uploadAndGeKeyword(MultipartFile[] multipartFiles, Long subjectId, String key);

}
