package com.spacifii.konstruct.explore.repository.explore;

import com.spacifii.konstruct.explore.entities.explore.MediaUserActionType;
import com.spacifii.konstruct.explore.entities.explore.ProjectUserAction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This is DAO class for ProjectUserAction
 */
@Repository
public interface ProjectUserActionRepository extends MongoRepository<ProjectUserAction,String> {

    /**
     * This repository method  is used to get UserActions on ProjectId and MediaUserActionType
     * @param projectId
     * @param mediaUserActionType
     * @return
     */
    List<ProjectUserAction> findByProjectIdAndMediaUserActionType(String projectId, MediaUserActionType mediaUserActionType);

    /**
     * This repository method is used to check if user had done Like on this project or Not
     * @param projectId
     * @param mediaUserActionType
     * @param createdBy
     * @return
     */
    ProjectUserAction findFirstByProjectIdAndMediaUserActionTypeAndCreatedBy(String projectId, MediaUserActionType mediaUserActionType, Long createdBy);
}
