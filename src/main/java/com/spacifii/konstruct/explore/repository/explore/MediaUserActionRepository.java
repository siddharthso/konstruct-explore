package com.spacifii.konstruct.explore.repository.explore;

import com.spacifii.konstruct.explore.entities.explore.MediaUserAction;
import com.spacifii.konstruct.explore.entities.explore.MediaUserActionType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This is DAO interface for MediaUserActionType
 */
@Repository
public interface MediaUserActionRepository extends MongoRepository<MediaUserAction,String> {

    /**
     * This method is used to return UserActions by mediaId and MediaUserActionType
     * @param mediaId
     * @param mediaUserActionType
     * @return
     */
    List<MediaUserAction> findByMediaIdAndMediaUserActionType(String mediaId, MediaUserActionType mediaUserActionType);

    /**
     * This method is used to return MediaUserAction by MediaId, MediaUserActionType, CreatedBy just used to check user had liked the image or not
     * @param mediaId
     * @param mediaUserActionType
     * @param createdBy
     * @return
     */
    MediaUserAction findFirstByMediaIdAndMediaUserActionTypeAndCreatedBy(String mediaId,MediaUserActionType mediaUserActionType, Long createdBy);
}
