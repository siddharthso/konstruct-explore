package com.spacifii.konstruct.explore.repository.conceptBoard;

import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardShare;
import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardShortList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This is DAO class for conceptBoardShortList
 */
@Repository
public interface ConceptBoardShortListRepository extends MongoRepository<ConceptBoardShortList,String> {

    /**
     * This Repository method is used to find ConceptBard was shortlisted or not earlier by a subejctId
     * @param conceptBoardId
     * @param conceptBoardMediaId
     * @param subjectId
     * @return
     */
    ConceptBoardShortList findFirstByConceptBoardIdAndAndConceptBoardMediaIdAndShortListedBySubjectId(String conceptBoardId, String conceptBoardMediaId, Long subjectId);


    /**
     * his Repository method is used to find ConceptBard was shortlisted or not earlier by a subejctId
     * @param conceptBoardId
     * @param conceptBoardMediaId
     * @param subjectId
     * @return
     */
    ConceptBoardShortList findFirstByConceptBoardIdAndAndConceptBoardMediaIdAndShortListedBySubjectIdAndIsShortListedTrue(String conceptBoardId, String conceptBoardMediaId, Long subjectId);


    /**
     * This Repository method is find ConceptBoardShortList via ConceptBoardId
     * @param conceptBoardId
     * @return
     */
    List<ConceptBoardShortList> findByConceptBoardId(String conceptBoardId);


    /**
     * This service repository method is used to get ConceptBoardShortList via ConceptBoardId and ShortListedTrue
     * @param conceptBoardId
     * @return
     */
    List<ConceptBoardShortList> findByConceptBoardIdAndIsShortListedTrue(String conceptBoardId);

    /**
     * This service repository method is used to get ConceptBoardShortList via ConceptBoardId and ShortListedBySubjectId and ShortListedTrue
     * @param conceptBoardId
     * @param subjectId
     * @return
     */
    List<ConceptBoardShortList> findByConceptBoardIdAndShortListedBySubjectIdAndIsShortListedTrue(String conceptBoardId, Long subjectId);

    /**
     * This Repository method is used to find ConceptBoadShotsLists by ConceptBoardId And MediaId
     * @param conceptBoardId
     * @param conceptBoardMediaId
     * @return
     */
    List<ConceptBoardShortList> findByConceptBoardIdAndConceptBoardMediaId(String conceptBoardId, String conceptBoardMediaId);


    /**
     * This Repository method is used to ConceptBoadShotsLists by ConceptBoardId and MediaId and IsShortListed is True
     * @param conceptBoardId
     * @param conceptBoardMediaId
     * @return
     */
    List<ConceptBoardShortList> findByConceptBoardIdAndConceptBoardMediaIdAndIsShortListedTrue(String conceptBoardId, String conceptBoardMediaId);

    /**
     * This Repository method is used to find ConceptBoardShortList by ConceptBoardId and ConceptBoardMediaId and ShortListedBySubjectId
     * @param conceptBoardId
     * @param conceptBoardMediaId
     * @param shortListedBySubjectId
     * @return
     */
    ConceptBoardShortList findFirstByConceptBoardIdAndConceptBoardMediaIdAndShortListedBySubjectId(String conceptBoardId, String conceptBoardMediaId, Long shortListedBySubjectId);

    /**
     * This repository method is used to ConceptBoardShortList by ConceptBoardId ConceptBoardMediaId and ShortlIstedBySubjectId
     * @param conceptBoardId
     * @param conceptBoardMediaId
     * @param shortListedBySubjectId
     * @return
     */
    List<ConceptBoardShortList> findByConceptBoardIdAndConceptBoardMediaIdAndShortListedBySubjectIdAndIsShortListedTrue(String conceptBoardId, String conceptBoardMediaId, Long shortListedBySubjectId);
}
