package com.spacifii.konstruct.explore.model.dto.conceptBoard;

import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardMedia;
import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardShortList;

public class MediaShortListMapping {

    private ConceptBoardMedia conceptBoardMedia;
    private ConceptBoardShortList conceptBoardShortList;

    public MediaShortListMapping() {
    }

    public MediaShortListMapping(ConceptBoardMedia conceptBoardMedia, ConceptBoardShortList conceptBoardShortList) {
        this.conceptBoardMedia = conceptBoardMedia;
        this.conceptBoardShortList = conceptBoardShortList;
    }

    public ConceptBoardMedia getConceptBoardMedia() {
        return conceptBoardMedia;
    }

    public void setConceptBoardMedia(ConceptBoardMedia conceptBoardMedia) {
        this.conceptBoardMedia = conceptBoardMedia;
    }

    public ConceptBoardShortList getConceptBoardShortList() {
        return conceptBoardShortList;
    }

    public void setConceptBoardShortList(ConceptBoardShortList conceptBoardShortList) {
        this.conceptBoardShortList = conceptBoardShortList;
    }
}
