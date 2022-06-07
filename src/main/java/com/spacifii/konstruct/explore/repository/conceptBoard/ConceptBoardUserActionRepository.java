package com.spacifii.konstruct.explore.repository.conceptBoard;

import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardUserAction;
import com.spacifii.konstruct.explore.entities.explore.MediaUserActionType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This is DAO class for ConceptBoardUserAction
 */
@Repository
public interface ConceptBoardUserActionRepository extends MongoRepository<ConceptBoardUserAction,String> {

    /**
     * This method is used to return UserActions by mediaId and MediaUserActionType
     * @param conceptBoardId
     * @param mediaUserActionType
     * @return
     */
    List<ConceptBoardUserAction> findByConceptBoardIdAndMediaUserActionType(String conceptBoardId, MediaUserActionType mediaUserActionType);

    /**
     * This method is used to return ConceptBoardMediaUserAction by MediaId, MediaUserActionType, CreatedBy just used to check user had liked the image or not
     * @param conceptBoardId
     * @param mediaUserActionType
     * @param createdBy
     * @return
     */
    ConceptBoardUserAction findFirstByConceptBoardIdAndMediaUserActionTypeAndCreatedBy(String conceptBoardId,MediaUserActionType mediaUserActionType, Long createdBy);
}
