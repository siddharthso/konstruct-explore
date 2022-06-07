package com.spacifii.konstruct.explore.service.explore;


import com.spacifii.konstruct.explore.entities.explore.Media;
import com.spacifii.konstruct.explore.entities.explore.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class SaveMediaStrategyContext {

    private static Map<MediaType,SaveMediaStrategy> strategyMap =  new HashMap<>();

    @Autowired
    @Qualifier("SaveMediaDocumentStrategy")
    SaveMediaStrategy saveMediaDocumentStrategy;

    @Autowired
    @Qualifier("SaveMediaExternalUrlStrategy")
    SaveMediaStrategy saveMediaExternalUrlStrategy;


    @Autowired
    @Qualifier("SaveMediaImage360Strategy")
    SaveMediaStrategy saveMediaImage360Strategy;

    @Autowired
    @Qualifier("SaveMediaImageStrategy")
    SaveMediaStrategy saveMediaImageStrategy;


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

    public Media saveMedia(MediaType  mediaType,Media media ,MultipartFile file){
        return strategyMap.get(mediaType).saveMedia(media,file);
    }

}
