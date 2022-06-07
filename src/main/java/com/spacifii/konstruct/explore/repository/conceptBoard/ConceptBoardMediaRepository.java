package com.spacifii.konstruct.explore.repository.conceptBoard;

import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardMedia;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * This is DAO class for ConceptBoardMedia
 */
@Repository
public interface ConceptBoardMediaRepository extends MongoRepository<ConceptBoardMedia,String> {

    /**
     * This repository method gets ConceptBoardMedia by SubjectId and Checksum
     * @param subjectId
     * @param checksum
     * @return
     */
    ConceptBoardMedia findFirstByCreatedByAndChecksum(Long subjectId, String checksum);

    /**
     * This repository method is used to get ConceptBoardMedia by id and SubjectId
     * @param id
     * @param subjectId
     * @return
     */
    ConceptBoardMedia findFirstByIdAndCreatedBy(String id, Long subjectId);
}
