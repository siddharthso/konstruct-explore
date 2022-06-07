package com.spacifii.konstruct.explore.service.conceptBoard.impl;


import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoard;
import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardMedia;
import com.spacifii.konstruct.explore.entities.explore.Media;
import com.spacifii.konstruct.explore.entities.explore.MediaType;
import com.spacifii.konstruct.explore.service.conceptBoard.SaveConceptBoardMediaStrategy;
import com.spacifii.konstruct.explore.service.explore.SaveMediaStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class SaveConceptBoardMediaStrategyContext {

    private static Map<MediaType,SaveConceptBoardMediaStrategy> strategyMap =  new HashMap<>();

    @Autowired
    @Qualifier("SaveConceptBoardMediaDocumentStrategy")
    SaveConceptBoardMediaStrategy saveMediaDocumentStrategy;

    @Autowired
    @Qualifier("SaveConceptBoardMediaExternalUrlStrategy")
    SaveConceptBoardMediaStrategy saveMediaExternalUrlStrategy;

    @Autowired
    @Qualifier("SaveConceptBoardMediaImage360Strategy")
    SaveConceptBoardMediaStrategy saveMediaImage360Strategy;

    @Autowired
    @Qualifier("SaveConceptBoardMediaImageStrategy")
    SaveConceptBoardMediaStrategy saveMediaImageStrategy;


    @PostConstruct
    public void fillMap(){
        strategyMap.put(MediaType.IMAGE,saveMediaImageStrategy);
        strategyMap.put(MediaType.IMAGE360,saveMediaImage360Strategy);
        strategyMap.put(MediaType.VIDEO,saveMediaExternalUrlStrategy);
        strategyMap.put(MediaType.VIDEO360,saveMediaExternalUrlStrategy);
        strategyMap.put(MediaType.CAD,saveMediaDocumentStrategy);
        strategyMap.put(MediaType.PDF,saveMediaDocumentStrategy);
        strategyMap.put(MediaType.WORD,saveMediaDocumentStrategy);
        strategyMap.put(MediaType.EXCEL,saveMediaDocumentStrategy);
        strategyMap.put(MediaType.POWERPOINT,saveMediaDocumentStrategy);
        strategyMap.put(MediaType.PHOTOSHOP,saveMediaDocumentStrategy);
        strategyMap.put(MediaType.SKETCHUP,saveMediaDocumentStrategy);
        strategyMap.put(MediaType.EXTERNALURL,saveMediaExternalUrlStrategy);

    }

    public ConceptBoardMedia saveMedia(MediaType  mediaType, ConceptBoard conceptBoard, ConceptBoardMedia media , MultipartFile file){
        return strategyMap.get(mediaType).saveMedia(conceptBoard,media,file);
    }

}
