package com.spacifii.konstruct.explore.repository.explore;

import com.spacifii.konstruct.explore.entities.explore.ExploreProject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This is DAO class for ExploreProject
 */
@Repository
public interface ExploreProjectRepository extends MongoRepository<ExploreProject,String> {

    /**
     * This method returns All ExploreProjects for a subjectId
     * @param subjectId
     * @return
     */
    List<ExploreProject> findBySubjectId(Long subjectId);

}
