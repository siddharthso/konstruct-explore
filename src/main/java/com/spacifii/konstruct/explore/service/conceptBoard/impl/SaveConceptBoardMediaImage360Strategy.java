package com.spacifii.konstruct.explore.service.conceptBoard.impl;

import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoard;
import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardMedia;
import com.spacifii.konstruct.explore.service.conceptBoard.SaveConceptBoardMediaStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service("SaveConceptBoardMediaImage360Strategy")
public class SaveConceptBoardMediaImage360Strategy implements SaveConceptBoardMediaStrategy {


    @Autowired
    SaveConceptBoardMediaImageStrategy saveConceptBoardMediaImageStrategy;

    @Override
    @Transactional
    public ConceptBoardMedia saveMedia(ConceptBoard conceptBoard, ConceptBoardMedia media, MultipartFile multipartFile) {
        return saveConceptBoardMediaImageStrategy.saveMedia(conceptBoard,media,multipartFile);
    }
}
