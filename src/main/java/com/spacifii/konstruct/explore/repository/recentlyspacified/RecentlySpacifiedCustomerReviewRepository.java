package com.spacifii.konstruct.explore.repository.recentlyspacified;


import com.spacifii.konstruct.explore.entities.recentlyspacified.RecentlySpacifiedCustomerReview;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This is DAO class for RecentlySpacifiedCustomerReview
 */
@Repository
public interface RecentlySpacifiedCustomerReviewRepository extends MongoRepository<RecentlySpacifiedCustomerReview,String> {


    /**
     * This repository method is use dto find RecentlySpacifiedCustomerReview by ProjectId and ReviewerSubjectId
     * @param projectId
     * @param subjectId
     * @return
     */
    RecentlySpacifiedCustomerReview findFirstByProjectIdAndReviewerSubjectId(String projectId, Long subjectId);

    /**
     * This repository method is used to find RecentlySpacifiedCustomerReview by ProjectId
     * @param projectId
     * @return
     */
    List<RecentlySpacifiedCustomerReview> findByProjectId(String projectId);

}
