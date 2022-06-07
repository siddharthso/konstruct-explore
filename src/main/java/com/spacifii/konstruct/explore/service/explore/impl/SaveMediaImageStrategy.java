package com.spacifii.konstruct.explore.service.explore.impl;

import com.spacifii.konstruct.explore.entities.explore.*;
import com.spacifii.konstruct.explore.service.explore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service("SaveMediaImageStrategy")
public class SaveMediaImageStrategy implements SaveMediaStrategy {

    @Autowired
    CloudVisionService cloudVisionService;

    @Autowired
    S3UploadDownloadService s3UploadDownloadService;

    @Autowired
    ExploreKeywordService exploreKeywordService;

    @Autowired
    ExploreKeywordElasticService exploreKeywordElasticService;

    @Autowired
    MediaService mediaService;

    @Autowired
    MediaCloudVIsionResponseMappingService mediaCloudVIsionResponseMappingService;


    @Override
    @Transactional
    public Media saveMedia(Media media, MultipartFile multipartFile) {


        List<CloudVisionResponse> stringFloatMap = cloudVisionService.detectLablesAndImageProperties(multipartFile);

        // We might end up in concurrentModification Exception that's why create new List
        List<CloudVisionResponse> responseToSave = new ArrayList<>();
        if(stringFloatMap != null && stringFloatMap.size() > 0) {
            for (CloudVisionResponse cloudVisionResponse : stringFloatMap) {

                if (cloudVisionResponse.getCloudVisionType().equals(CloudVisionType.LABEL_DETECTION)) {

                    List<ExploreKeywordElastic> exploreKeywordElastics = exploreKeywordElasticService.
                            searchFuzzyForKeyword(cloudVisionResponse.getLabel());

                    Set<String> strings = media.getKeywords();
                    strings.add(cloudVisionResponse.getLabel().toUpperCase());
                    media.setKeywords(strings);

                    if (exploreKeywordElastics != null && exploreKeywordElastics.size() > 0) {
                        /*for (ExploreKeywordElastic exploreKeywordElastic : exploreKeywordElastics) {
                            //ExploreKeyword exploreKeyword = exploreKeywordService.getExploreKeywordByKeyword(exploreKeywordElastic.getKeyword());
                            Set<String> strings = media.getKeywords();
                            strings.add(exploreKeywordElastic.getKeyword().toUpperCase());
                            media.setKeywords(strings);
                        }*/
                    } else {
                        ExploreKeyword exploreKeyword = new ExploreKeyword();
                        exploreKeyword.setKeyword(cloudVisionResponse.getLabel());
                        exploreKeyword.setKeywordDisplay(cloudVisionResponse.getLabel());
                        exploreKeyword.setDescription(cloudVisionResponse.getLabel());
                        exploreKeyword.setKeywordCategory(KEYWORD_CATEGORY.SPACE);
                        exploreKeyword.setKeywordSource(KEYWORD_SOURCE.GOOGLECV);
                        exploreKeyword = exploreKeywordService.save(exploreKeyword, media.getSubjectId());
                        /*Set<String> strings = media.getKeywords();
                        strings.add(exploreKeyword.getKeyword());
                        media.setKeywords(strings);*/
                    }
                    responseToSave.add(cloudVisionResponse);
                } else if(cloudVisionResponse.getCloudVisionType().equals(CloudVisionType.IMAGE_PROPERTIES)) {

                    CloudVisionResponse cloudVisionResponse1 = ImagePropertiesCalculator.
                            updateCloudVisionImgeResponseAttributes(cloudVisionResponse);
                    Set<String> colors = media.getColors();
                    Set<String> colorsAndHex = media.getColorsAndHex();
                    colors.add(cloudVisionResponse1.getClosestColorName());
                    colors.add(cloudVisionResponse1.getParentColorName());

                    colorsAndHex.add(cloudVisionResponse1.getPrimaryColorHex());
                    colorsAndHex.add(cloudVisionResponse1.getPrimaryColorHexHash());
                    colorsAndHex.add(cloudVisionResponse1.getHexCode());
                    colorsAndHex.add(cloudVisionResponse1.getHashHexCode());
                    colorsAndHex.addAll(colors);

                    media.setColors(colors);
                    Set<String> keywords = media.getKeywords();
                    keywords.addAll(colors);
                    media.setKeywords(keywords);
                    media.setColorsAndHex(colorsAndHex);
                    responseToSave.add(cloudVisionResponse);
                }
                else {

                    List<ObjectLocalizationContainer> objectLocalizationContainers = media.getObjectLocalizationContainers();
                    objectLocalizationContainers.add(new ObjectLocalizationContainer(cloudVisionResponse.getLabel(),cloudVisionResponse.getScore(),cloudVisionResponse.getVerticesList()));
                    media.setObjectLocalizationContainers(objectLocalizationContainers);
                    responseToSave.add(cloudVisionResponse);

                }
            }
        }
        media = s3UploadDownloadService.uploadFile(multipartFile, media);
        media =  mediaService.save(media);
        mediaCloudVIsionResponseMappingService.save(new MediaCloudVIsionResponseMapping(media.getId(),responseToSave));
        return media;
    }
}
