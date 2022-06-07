package com.spacifii.konstruct.explore.repository.conceptBoard;

import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardShare;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * This is DAO class for ConceptBoardShare
 */
@Repository
public interface ConceptBoardShareRepository extends MongoRepository<ConceptBoardShare,String> {

    /**
     * This repository method is used to find ConceptBoardShare by ConceptBoardId and Invitee SubjectId
     * @param conceptBoardId
     * @param subjectId
     * @return
     */
    ConceptBoardShare findFirstByConceptBoardIdAndInviteeSubjectId(String conceptBoardId, Long subjectId);

    /**
     *
     * This repository method is used to check conceptBoardShares by ConceptBoardId
     * @param conceptBoardId
     * @return
     */
    List<ConceptBoardShare> findByConceptBoardIdAndIsRevokedFalse(String conceptBoardId);

    /**
     * This repository method is used to check if ConceptBoard By Invitee SubjectId
     * @param subjectId
     * @return
     */
    List<ConceptBoardShare> findByInviteeSubjectIdAndIsRevokedFalse(Long subjectId);


    /**
     * This repository method is used to check when something is pending is anything is not Mapped to user.
     * @param email
     * @return
     */
    List<ConceptBoardShare> findByEmailAndInviteeSubjectIdNull(String email);

    /**
     * This service method is used to get ConceptBoards which were shared with EmailIds collection
     * @param conceptBoardId
     * @param emailids
     * @return
     */
    List<ConceptBoardShare> findByConceptBoardIdAndEmailInAndIsRevokedFalse(String conceptBoardId, Set<String> emailids);


    /**
     * This service method is used to get ConceptBoards which were shared with EmailIds collection
     * @param conceptBoardId
     * @param emailids
     * @return
     */
    List<ConceptBoardShare> findByConceptBoardIdAndEmailIn(String conceptBoardId, Set<String> emailids);


}
