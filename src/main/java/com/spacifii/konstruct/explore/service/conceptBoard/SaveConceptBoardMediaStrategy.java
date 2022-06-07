package com.spacifii.konstruct.explore.service.conceptBoard;

import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoard;
import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardMedia;
import org.springframework.web.multipart.MultipartFile;

public interface SaveConceptBoardMediaStrategy {

    ConceptBoardMedia saveMedia(ConceptBoard conceptBoard, ConceptBoardMedia media, MultipartFile multipartFile);
}
