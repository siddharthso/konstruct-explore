package com.spacifii.konstruct.explore.repository.explore;

import com.spacifii.konstruct.explore.entities.explore.ExploreWalkThrough;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This is DAO class for ExploreWalkThrough
 */
@Repository
public interface ExploreWalkThroughRepository extends MongoRepository<ExploreWalkThrough,String> {

    /**
     * This method is use to get ExploreWalkThroughs by projectId
     * @param projectId
     * @return
     */
    List<ExploreWalkThrough> findByProjectId(String projectId);
}
