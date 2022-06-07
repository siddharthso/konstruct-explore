package com.spacifii.konstruct.explore.service.explore;

import com.spacifii.konstruct.explore.entities.explore.Media;
import com.spacifii.konstruct.explore.entities.generalMedia.GeneralMedia;
import org.springframework.web.multipart.MultipartFile;

/**
 * This is service class for AmazonS3 Bucket Upload Download
 */
public interface S3UploadDownloadService {


    /**
     * This is test class for downloading file
     * @param keyName
     */
    void downloadFile(String keyName);

    /**
     * This is test class for uploading file
     * @param keyName
     * @param uploadFilePath
     */
    void uploadFile(String keyName, String uploadFilePath);

    /**
     * This method is used to upload exploreMedia
     * @param multipartFile
     * @param media
     * @return
     */
    Media uploadFile(MultipartFile multipartFile, Media media);

    /**
     * This method is used to upload GeneralMedia
     * @param multipartFile
     * @param generalMedia
     * @return
     */
    GeneralMedia uploadGeneralMedia(MultipartFile multipartFile,GeneralMedia generalMedia, String key);
}
