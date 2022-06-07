package com.spacifii.konstruct.explore.service.conceptBoard.impl;

import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoard;
import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardMedia;
import com.spacifii.konstruct.explore.repository.conceptBoard.ConceptBoardMediaRepository;
import com.spacifii.konstruct.explore.repository.conceptBoard.ConceptBoardRepository;
import com.spacifii.konstruct.explore.service.conceptBoard.SaveConceptBoardMediaStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Service("SaveConceptBoardMediaExternalUrlStrategy")
public class SaveConceptBoardMediaExternalUrlStrategy implements SaveConceptBoardMediaStrategy {


    @Autowired
    ConceptBoardMediaRepository conceptBoardMediaRepository;

    @Autowired
    ConceptBoardRepository conceptBoardRepository;

    @Override
    @Transactional
    public ConceptBoardMedia saveMedia(ConceptBoard conceptBoard, ConceptBoardMedia media, MultipartFile multipartFile) {
        media =  conceptBoardMediaRepository.save(media);
        return media;
    }
}
