package com.spacifii.konstruct.explore.repository.conceptBoard;

import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardMediaUserAction;
import com.spacifii.konstruct.explore.entities.explore.MediaUserActionType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This is DAO class for ConceptBoardMediaUserAction
 */
@Repository
public interface ConceptBoardMediaUserActionRepository extends MongoRepository<ConceptBoardMediaUserAction, String> {

    /**
     * This method is used to return UserActions by mediaId and MediaUserActionType
     * @param mediaId
     * @param mediaUserActionType
     * @return
     */
    List<ConceptBoardMediaUserAction> findByMediaIdAndMediaUserActionType(String mediaId, MediaUserActionType mediaUserActionType);

    /**
     * This method is used to return ConceptBoardMediaUserAction by MediaId, MediaUserActionType, CreatedBy just used to check user had liked the image or not
     * @param mediaId
     * @param mediaUserActionType
     * @param createdBy
     * @return
     */
    ConceptBoardMediaUserAction findFirstByMediaIdAndMediaUserActionTypeAndCreatedBy(String mediaId,MediaUserActionType mediaUserActionType, Long createdBy);
}
