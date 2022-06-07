package com.spacifii.konstruct.explore.service.conceptBoard.impl;

import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoard;
import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardMedia;
import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardMediaType;
import com.spacifii.konstruct.explore.entities.explore.*;
import com.spacifii.konstruct.explore.entities.generalMedia.GeneralMedia;
import com.spacifii.konstruct.explore.exception.conceptBoard.ConceptBoardMediaDuplicateForSameSubjectException;
import com.spacifii.konstruct.explore.repository.conceptBoard.ConceptBoardMediaRepository;
import com.spacifii.konstruct.explore.repository.conceptBoard.ConceptBoardRepository;
import com.spacifii.konstruct.explore.service.conceptBoard.ConceptBoardMediaService;
import com.spacifii.konstruct.explore.service.conceptBoard.SaveConceptBoardMediaStrategy;
import com.spacifii.konstruct.explore.service.explore.*;
import com.spacifii.konstruct.explore.service.explore.impl.ImagePropertiesCalculator;
import com.spacifii.konstruct.explore.service.generalMedia.GeneralMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service("SaveConceptBoardMediaImageStrategy")
public class SaveConceptBoardMediaImageStrategy implements SaveConceptBoardMediaStrategy {

    @Autowired
    CloudVisionService cloudVisionService;

    @Autowired
    S3UploadDownloadService s3UploadDownloadService;

    @Autowired
    ConceptBoardMediaService conceptBoardMediaService;

    @Autowired
    MediaCloudVIsionResponseMappingService mediaCloudVIsionResponseMappingService;

    @Autowired
    private GeneralMediaService generalMediaService;

    @Autowired
    ConceptBoardMediaRepository conceptBoardMediaRepository;

    @Autowired
    ConceptBoardRepository conceptBoardRepository;



    @Override
    @Transactional
    public ConceptBoardMedia saveMedia(ConceptBoard conceptBoard, ConceptBoardMedia media, MultipartFile multipartFile) {


        List<CloudVisionResponse> stringFloatMap = cloudVisionService.detectLablesAndImageProperties(multipartFile);

        // We might end up in concurrentModification Exception that's why create new List
        List<CloudVisionResponse> responseToSave = new ArrayList<>();
        if(stringFloatMap != null && stringFloatMap.size() > 0) {
            for (CloudVisionResponse cloudVisionResponse : stringFloatMap) {

                if (cloudVisionResponse.getCloudVisionType().equals(CloudVisionType.LABEL_DETECTION)) {
                            Set<String> strings = media.getKeywords();
                            strings.add(cloudVisionResponse.getLabel().toUpperCase());
                            media.setKeywords(strings);


                    responseToSave.add(cloudVisionResponse);
                }  else if(cloudVisionResponse.getCloudVisionType().equals(CloudVisionType.IMAGE_PROPERTIES)){

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
                } else {

                    List<ObjectLocalizationContainer> objectLocalizationContainers = media.getObjectLocalizationContainers();
                    objectLocalizationContainers.add(new ObjectLocalizationContainer(cloudVisionResponse.getLabel(),cloudVisionResponse.getScore(),cloudVisionResponse.getVerticesList()));
                    media.setObjectLocalizationContainers(objectLocalizationContainers);
                    responseToSave.add(cloudVisionResponse);

                }
            }
        }
        GeneralMedia generalMedia = generalMediaService.upload(new MultipartFile[]{multipartFile},media.getCreatedBy(),"CONCEPTBOARD").get(0);

        Map<String,String> checksum = new HashMap<>();
        for (ConceptBoardMedia conceptBoardMedia: conceptBoard.getMedias()) {
            checksum.put(conceptBoardMedia.getChecksum(),conceptBoardMedia.getChecksum());
        }

        if(checksum.get(generalMedia.getChecksum())== null){
            media.setChecksum(generalMedia.getChecksum());
            media.setConceptBoardMediaType(ConceptBoardMediaType.SELF_UPLOADED);
            media.setUrl(generalMedia.getUrl());
            media.setAltUrl(generalMedia.getAltUrl());
  //          media.setKeywords(generalMedia.getKeywords());
            media.preSave(media.getCreatedBy());
            media = conceptBoardMediaRepository.save(media);
        } else {
            throw new ConceptBoardMediaDuplicateForSameSubjectException(generalMedia.getOriginalFilename());
        }
       // media =  mediaService.save(media);
        mediaCloudVIsionResponseMappingService.save(new MediaCloudVIsionResponseMapping(media.getId(),responseToSave));
        return media;
    }
}
