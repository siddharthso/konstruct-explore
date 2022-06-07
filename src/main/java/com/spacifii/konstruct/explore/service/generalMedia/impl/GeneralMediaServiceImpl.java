package com.spacifii.konstruct.explore.service.generalMedia.impl;

import com.spacifii.konstruct.explore.entities.generalMedia.GeneralMedia;
import com.spacifii.konstruct.explore.repository.generalMedia.GeneralMediaRepository;
import com.spacifii.konstruct.explore.service.explore.CloudVisionService;
import com.spacifii.konstruct.explore.service.explore.S3UploadDownloadService;
import com.spacifii.konstruct.explore.service.explore.UtilityService;
import com.spacifii.konstruct.explore.service.generalMedia.GeneralMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

/**
 * This is service class for GeneralMedia
 */
@Service
public class GeneralMediaServiceImpl implements GeneralMediaService {


    @Autowired
    UtilityService utilityService;

    @Autowired
    S3UploadDownloadService s3UploadDownloadService;

    @Autowired
    GeneralMediaRepository generalMediaRepository;

    @Autowired
    CloudVisionService cloudVisionService;


    @Override
    public List<GeneralMedia> upload(MultipartFile[] multipartFiles, Long subjectId, String key) {

        List<GeneralMedia> generalMedias = new ArrayList<>();

        for (MultipartFile multipartFile: multipartFiles) {
            GeneralMedia generalMedia = new GeneralMedia();
            String filename = multipartFile.getOriginalFilename();
            String checksum = utilityService.getImageHash(multipartFile);
            generalMedia.setExtension(getFileExtension(filename));
            generalMedia.setChecksum(checksum);
            generalMedia.setOriginalFilename(multipartFile.getOriginalFilename());
            generalMedia = s3UploadDownloadService.uploadGeneralMedia(multipartFile,generalMedia,key);
            generalMedia.preSave(subjectId);
            generalMedias.add(generalMediaRepository.save(generalMedia));
        }

        return generalMedias;
    }

    @Override
    public List<GeneralMedia> uploadAndGeKeyword(MultipartFile[] multipartFiles, Long subjectId, String key) {

        List<GeneralMedia> generalMedias = new ArrayList<>();

        for (MultipartFile multipartFile: multipartFiles) {
            GeneralMedia generalMedia = new GeneralMedia();
            generalMedia.setOriginalFilename(multipartFile.getOriginalFilename());
            generalMedia.setExtension(getFileExtension(multipartFile.getOriginalFilename()));
            generalMedia.setChecksum(utilityService.getImageHash(multipartFile));
            generalMedia.setKeywords(cloudVisionService.detectLables(multipartFile).keySet());
            generalMedia = s3UploadDownloadService.uploadGeneralMedia(multipartFile,generalMedia,key);
            //TODO: CHECK IMAGE TYPES ONLY

            generalMedia.preSave(subjectId);
            generalMedias.add(generalMediaRepository.save(generalMedia));
        }

        return generalMedias;
    }

    /**
     * This private method gets Extension from Filename String
     * @param fileName
     * @return
     */
    private static String getFileExtension(String fileName) {

        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1).toUpperCase();
        else return "";
    }

}
