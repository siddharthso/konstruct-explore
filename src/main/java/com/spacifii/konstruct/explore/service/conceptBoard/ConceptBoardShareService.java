package com.spacifii.konstruct.explore.service.conceptBoard;

import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardShare;
import com.spacifii.konstruct.explore.model.dto.conceptBoard.ConceptBoardEmailShareProfileDto;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This service class manages ConceptBoardShare
 */
public interface ConceptBoardShareService {


    /**
     * This method is used to fix my shares where Someone has shared me ConceptBoard which I dont have access to because never registered
     * @param subjectId
     */
    void fixMyShare(Long subjectId);

    /**
     * This service method is used to save or updateConceptBoardShares
     * @param emailIds
     * @param conceptBoardId
     * @param subjectId
     * @param message
     * @return
     */
    List<ConceptBoardShare> addUpdateEmailIdsForSharingConceptBoard(Set<String> emailIds, String conceptBoardId, String message ,Long subjectId);

    /**
     * This service method is used to add EmailIds for Sharing conceptBoard
     * @param emailIds
     * @param conceptBoardId
     * @param message
     * @param subjectId
     * @return
     */
    List<ConceptBoardShare> addEmailsIdForSharingConceptBoard(Set<String> emailIds, String conceptBoardId, String message ,Long subjectId);

    /**
     * This service method is used to remove EmailIds for Sharing conceptBoard
     * @param emailIds
     * @param conceptBoardId
     * @param subjectId
     * @return
     */
    List<ConceptBoardShare> removeEmailsIdForSharingConceptBoard(Set<String> emailIds, String conceptBoardId,Long subjectId);


    /**
     * This service method  is used to get EmailIds for Users which we have shared ConceptBoard
     * @param conceptBoardId
     * @return
     */
    Set<String> getShareListForconceptBoard(String conceptBoardId, Long subejctId);

    /**
     * This service method is used to get All ConceptBoardIds which are shared to a subject
     * @return
     */
    Set<String> getAllConcetpBoardIdsSharedToMe( Long subjectId);

    /**
     * This service method is used to get EmailIds and Profile (if present) for the users which we have shared ConceptBoard
     * @param conceptBoardId
     * @param subjectId
     * @return
     */
    List<ConceptBoardEmailShareProfileDto> getShareListWithProfileForconceptBoard(String conceptBoardId, Long subjectId);

    /**
     * THis service method is used to find conceptBoardShare via ConceptBoardId and SubjectId
     * @param conceptBoardId
     * @param subjectId
     * @return
     */
    ConceptBoardShare findByConceptBoardIdAndSubjectId(String conceptBoardId, Long subjectId);

    /**
     * This service method is used to
     * @param conceptBoardId
     * @param subjectId
     * @return
     */
    Boolean unshareMeFromSharedConceptBoard(String conceptBoardId, Long subjectId);

    /**
     * This service method is used get  Map of ConceptBoardShare via Emailids
     * @param conceptBoardId
     * @param emailIds
     * @return
     */
    Map<String,ConceptBoardShare> getConceptBoardShareMapViaEmailIds(String conceptBoardId, Set<String> emailIds);

}
