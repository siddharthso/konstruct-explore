package com.spacifii.konstruct.explore.repository.conceptBoard;

import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardWalkThrough;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This is DAO class for ConceptBoardWalkThrough
 */
@Repository
public interface ConceptBoardWalkThroughRepository extends MongoRepository<ConceptBoardWalkThrough,String> {

    /**
     * This repository method is used to find conceptBoardWalkThrough by ConceptBoardId
     * @param conceptBoardId
     * @return
     */
    List<ConceptBoardWalkThrough> findByConceptBoardId(String conceptBoardId);
}
