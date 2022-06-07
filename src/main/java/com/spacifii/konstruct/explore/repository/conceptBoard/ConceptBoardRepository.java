package com.spacifii.konstruct.explore.repository.conceptBoard;

import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoard;
import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardVisibility;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This is DAO class for ConceptBoard
 */
@Repository
public interface ConceptBoardRepository extends MongoRepository<ConceptBoard,String> {


    /**
     * This repository method is use to get conceptBoard By subjectId and folderName
     * @param subjectId
     * @param folderName
     * @return
     */
    ConceptBoard findFirstBySubjectIdAndFolderName(Long subjectId, String folderName);

    /**
     * This repository method is used to get conceptBoard by id and subjectId
     * @param id
     * @param subjectId
     * @return
     */
    ConceptBoard findFirstByIdAndSubjectId(String id, Long subjectId);

    /**
     * This repository method is used to find conceptBoards by subjectId
     * @param subjectId
     * @return
     */
    List<ConceptBoard> findBySubjectId(Long subjectId);

    /**
     * This repository method is used to find conceptBoards by subjectId and visibility
     * @param subjectId
     * @param conceptBoardVisibility
     * @return
     */
    List<ConceptBoard> findBySubjectIdAndConceptBoardVisibility(Long subjectId, ConceptBoardVisibility conceptBoardVisibility);


    /**
     * This repository method is used to find ConceptBoards by ParentConceptBoard and SubjectId
     * @param parentConceptBoard
     * @param subjectId
     * @return
     */
    List<ConceptBoard> findByParentConceptBoardAndSubjectId(String parentConceptBoard, Long subjectId);

    /**
     * This service method is used to find ChildBoards by ParentConceptBoardId
     * @param parentConceptBoardId
     * @return
     */
    List<ConceptBoard> findByParentConceptBoard(String parentConceptBoardId);
}
