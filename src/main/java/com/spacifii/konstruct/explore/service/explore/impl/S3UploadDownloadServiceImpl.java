package com.spacifii.konstruct.explore.service.explore.impl;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import com.spacifii.konstruct.explore.entities.explore.Media;
import com.spacifii.konstruct.explore.entities.generalMedia.GeneralMedia;
import com.spacifii.konstruct.explore.service.explore.S3UploadDownloadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * This is service class for AmazonS3 Bucket Upload Download
 */
@Service
public class S3UploadDownloadServiceImpl implements S3UploadDownloadService {

    private Logger logger = LoggerFactory.getLogger(S3UploadDownloadServiceImpl.class);

    @Autowired
    private AmazonS3 s3client;

    @Value("${s3.bucket}")
    private String bucketName;

    @Value("${s3.uploadPath}")
    private String uploadPath;

    @Value("${S3BasePath}")
    private String s3BasePath;

    @Value("${image.url}")
    private String imageUrl;

    @Value("${S3DirectPath}")
    private String directUploadPath;

    @Value("${S3ConceptBoardPath}")
    private String conceptBoardUploadPath;

    @Value("${S3ExplorePath}")
    private String exploreUploadPath;

    @Value("${S3UserProfilePath}")
    private String userProfileUploadPath;

    @Value("${S3AgreementPath}")
    private String agreementUploadPath;

    @Value("${S3UserAgreementPath}")
    private String userAgreementUploadPath;


    @Value("${S3OfferingPath}")
    private String offeringUploadPath;


    /**
     * This is test class for downloading file
     * @param keyName
     */
    @Override
    public void downloadFile(String keyName) {

        try {

            System.out.println("Downloading an object");
            S3Object s3object = s3client.getObject(new GetObjectRequest(bucketName, keyName));
            System.out.println("Content-Type: "  + s3object.getObjectMetadata().getContentType());
            Utility.displayText(s3object.getObjectContent());
            logger.info("===================== Import File - Done! =====================");

        } catch (AmazonServiceException ase) {
            logger.info("Caught an AmazonServiceException from GET requests, rejected reasons:");
            logger.info("Error Message:    " + ase.getMessage());
            logger.info("HTTP Status Code: " + ase.getStatusCode());
            logger.info("AWS Error Code:   " + ase.getErrorCode());
            logger.info("Error Type:       " + ase.getErrorType());
            logger.info("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            logger.info("Caught an AmazonClientException: ");
            logger.info("Error Message: " + ace.getMessage());
        } catch (IOException ioe) {
            logger.info("IOE Error Message: " + ioe.getMessage());
        }
    }

    /**
     * This is test class for uploading file
     * @param keyName
     * @param uploadFilePath
     */
    @Override
    public void uploadFile(String keyName, String uploadFilePath) {

        try {

            File file = new File(uploadFilePath);

            s3client.putObject(new PutObjectRequest(bucketName, keyName, file));
            logger.info("===================== Upload File - Done! =====================");

        } catch (AmazonServiceException ase) {
            AmazonExceptionLog(ase);
        } catch (AmazonClientException ace) {
            logger.info("Caught an AmazonClientException: ");
            logger.info("Error Message: " + ace.getMessage());
        }
    }

    /**
     * This method is used to upload exploreMedia
     * @param multipartFile
     * @param media
     * @return
     */
    @Override
    public Media uploadFile(MultipartFile multipartFile, Media media) {
        try {
            String s3UploadPath = getStringFromKey("EXPLORE");
            byte[] bytes = multipartFile.getBytes();
            String filename= media.getChecksum()+"."+media.getMediaTypeExtension();
            Path path = Paths.get(uploadPath +filename);
            Files.write(path, bytes);

            File file = new File(uploadPath+filename);
             multipartFile.transferTo(file);
             //multipartFile.getBytes();
            s3client.putObject(new PutObjectRequest(bucketName, s3UploadPath+filename, file));
            logger.info("===================== Upload File - Done! =====================");
            Files.delete(Paths.get(uploadPath + filename));
            media.setFilename(filename);
            media.setUrl(imageUrl+s3UploadPath+filename);
            media.setAltUrl(s3BasePath+s3UploadPath+filename);
            return media;
        } catch (AmazonServiceException ase) {
            AmazonExceptionLog(ase);
        } catch (AmazonClientException ace) {
            logger.info("Caught an AmazonClientException: ");
            logger.info("Error Message: " + ace.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method is used to upload GeneralMedia
     * @param multipartFile
     * @param generalMedia
     * @param key
     * @return
     */
    @Override
    public GeneralMedia uploadGeneralMedia(MultipartFile multipartFile, GeneralMedia generalMedia, String key) {

        try {
            String s3UploadPath = getStringFromKey(key);
            byte[] bytes = multipartFile.getBytes();
            String filename= generalMedia.getChecksum()+System.currentTimeMillis()+"."+generalMedia.getExtension();
            Path path = Paths.get(uploadPath +filename);
            Files.write(path, bytes);

            File file = new File(uploadPath+filename);
            multipartFile.transferTo(file);
            //multipartFile.getBytes();
            s3client.putObject(new PutObjectRequest(bucketName, s3UploadPath+filename, file));
            logger.info("===================== Upload File - Done! =====================");
            Files.delete(Paths.get(uploadPath + filename));
            generalMedia.setFileName(filename);
            generalMedia.setUrl(imageUrl+s3UploadPath+filename);
            generalMedia.setAltUrl(s3BasePath+s3UploadPath+filename);

            return generalMedia;
        } catch (AmazonServiceException ase) {
            AmazonExceptionLog(ase);
        } catch (AmazonClientException ace) {
            logger.info("Caught an AmazonClientException: ");
            logger.info("Error Message: " + ace.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    private void AmazonExceptionLog(AmazonServiceException ase) {
        logger.info("Caught an AmazonServiceException from PUT requests, rejected reasons:");
        logger.info("Error Message:    " + ase.getMessage());
        logger.info("HTTP Status Code: " + ase.getStatusCode());
        logger.info("AWS Error Code:   " + ase.getErrorCode());
        logger.info("Error Type:       " + ase.getErrorType());
        logger.info("Request ID:       " + ase.getRequestId());
    }


    /**
     * This returns uploadPath for AmazonS3Bucket
     * @param key
     * @return
     */
    private String getStringFromKey(String key){
        if(key.equals("DIRECT")){
            return directUploadPath;
        } else if(key.equals("EXPLORE")){
            return exploreUploadPath;
        } else if(key.equals("CONCEPTBOARD")){
            return conceptBoardUploadPath;
        } else if(key.equals("AGREEMENT")){
            return agreementUploadPath;
        } else if(key.equals("USERPROFILE")){
            return userProfileUploadPath;
        } else if(key.equals("USERAGREEMENT")){
            return userAgreementUploadPath;
        } else if(key.equals("OFFERING")){
            return offeringUploadPath;
        }
        return null;
    }

}



