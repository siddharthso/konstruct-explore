package com.spacifii.konstruct.explore.service.explore.impl;


import com.spacifii.konstruct.explore.elasticRepository.ExploreKeywordElasticRepository;
import com.spacifii.konstruct.explore.entities.explore.ExploreKeywordElastic;
import com.spacifii.konstruct.explore.service.explore.S3UploadDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    S3UploadDownloadService s3UploadDownloadService;


    @Autowired
    ExploreKeywordElasticRepository exploreKeywordElasticRepository;


    @Value("${s3.uploadfile}")
    private String uploadFilePath;

    @Value("${s3.key}")
    private String downloadKey;


    //@PostConstruct
    public void uploadTest(){

        System.out.println("---------------- START UPLOAD FILE ----------------");
        s3UploadDownloadService.uploadFile("jsa-s3-upload-file.txt", uploadFilePath);
        System.out.println("---------------- START DOWNLOAD FILE ----------------");
        s3UploadDownloadService.downloadFile(downloadKey);
    }



    //@PostConstruct
    public void doSomething(){

        Page<ExploreKeywordElastic> page =  exploreKeywordElasticRepository.
                findFuzzyMatchStringWithPage("to",new PageRequest(0,10)) ;
        System.out.printf("something" + page.toString());

        List<ExploreKeywordElastic> list = exploreKeywordElasticRepository.findFuzzyMatchString("to");
        System.out.println("something More" + list.toString());

    }

}
