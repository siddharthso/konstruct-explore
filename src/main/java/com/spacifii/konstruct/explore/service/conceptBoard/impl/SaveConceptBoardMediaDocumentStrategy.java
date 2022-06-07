package com.spacifii.konstruct.explore.service.conceptBoard.impl;

import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoard;
import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardMedia;
import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardMediaType;
import com.spacifii.konstruct.explore.entities.generalMedia.GeneralMedia;
import com.spacifii.konstruct.explore.exception.conceptBoard.ConceptBoardMediaDuplicateForSameSubjectException;
import com.spacifii.konstruct.explore.repository.conceptBoard.ConceptBoardMediaRepository;
import com.spacifii.konstruct.explore.repository.conceptBoard.ConceptBoardRepository;
import com.spacifii.konstruct.explore.service.conceptBoard.SaveConceptBoardMediaStrategy;
import com.spacifii.konstruct.explore.service.generalMedia.GeneralMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service("SaveConceptBoardMediaDocumentStrategy")
public class SaveConceptBoardMediaDocumentStrategy implements SaveConceptBoardMediaStrategy {


    @Autowired
    SaveConceptBoardMediaImageStrategy saveConceptBoardMediaImageStrategy;

    @Autowired
    GeneralMediaService generalMediaService;

    @Autowired
    ConceptBoardRepository conceptBoardRepository;

    @Autowired
    ConceptBoardMediaRepository conceptBoardMediaRepository;



    @Override
    @Transactional
    public ConceptBoardMedia saveMedia(ConceptBoard conceptBoard, ConceptBoardMedia media, MultipartFile multipartFile) {
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
            media.setKeywords(generalMedia.getKeywords());
            media.preSave(media.getCreatedBy());
            media = conceptBoardMediaRepository.save(media);

        } else {
            throw new ConceptBoardMediaDuplicateForSameSubjectException(generalMedia.getOriginalFilename());
        }
        // media =  mediaService.save(media);
        return media;
    }
}
