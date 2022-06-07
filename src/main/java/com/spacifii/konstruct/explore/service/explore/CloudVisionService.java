package com.spacifii.konstruct.explore.service.explore;

import com.spacifii.konstruct.explore.entities.explore.CloudVisionResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * This service is interacts with GoogleCloudVision
 */
public interface CloudVisionService {


    /**
     * This service method is used to detect labels from Image
     * @param multipartFile
     * @return
     */
    Map<String, Float> detectLables(MultipartFile multipartFile);

    /**
     * This service method is used to detect labels and ImageProperties from Image
     * @param multipartFile
     * @return
     */
    List<CloudVisionResponse> detectLablesAndImageProperties(MultipartFile multipartFile);


}
