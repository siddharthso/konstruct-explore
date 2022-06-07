package com.spacifii.konstruct.explore.service.conceptBoard;

import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardShortList;
import com.spacifii.konstruct.explore.model.dto.conceptBoard.EnvelopedConceptBoardShortList;

import java.util.List;

/**
 * This service class is used to manage ConceptBoardShortList
 */
public interface ConceptBoardShortListService {

    /**
     * This service method is used to shortList Media
     * @param conceptBoardId
     * @param conceptBoardMediaId
     * @param comment
     * @param SubjectId
     * @return
     */
    ConceptBoardShortList shortListConceptBoardMedia(String conceptBoardId, String conceptBoardMediaId, String comment, Long SubjectId);

    /**
     * This service method is used to unshortList a Media
     * @param conceptBoardId
     * @param conceptBoardMediaId
     * @param comment
     * @param SubjectId
     * @return
     */
    Boolean unShortListConceptBoardMedia(String conceptBoardId, String conceptBoardMediaId, String comment, Long SubjectId);

    /**
     * This service method is used to get Shortlisted ConceptBoard For myself
     * @param conceptBoardId
     * @param subjectId
     * @return
     */
    List<ConceptBoardShortList> getMyShortListedImagesForConceptBoard(String conceptBoardId, Long subjectId);

    /**
     * This service method is used to get ConceptBoardShortList for user for MediaId
     * @param conceptBoardId
     * @param conceptBoardMediaId
     * @param subjectId
     * @return
     */
    ConceptBoardShortList getMyConceptBoardShortListForConceptBoardAndConceptBoardMediaId(String conceptBoardId, String conceptBoardMediaId, Long subjectId);

    /**
     * This service method is used to get ShortListed Images for EmailId User
     * @param conceptBoardId
     * @param emailId
     * @param subjectId
     * @return
     */
    EnvelopedConceptBoardShortList getShortListedImageForConceptBoardByEmailId(String conceptBoardId, String emailId, Long subjectId);


    /**
     * This service method is used to get MediaListMapping Images for EmailId User
     * @param conceptBoardId
     * @param emailId
     * @param subjectId
     * @return
     */
    EnvelopedConceptBoardShortList getMediaShortListMappingForConceptBoardByEmailId(String conceptBoardId, String emailId, Long subjectId);

}

