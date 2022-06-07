package com.spacifii.konstruct.explore.repository.recentlyspacified;


import com.spacifii.konstruct.explore.entities.recentlyspacified.CustomerTestimonialInitiateRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * This is DAO class for CustomerTestimonialInitiateRequest
 */
@Repository
public interface CustomerTestimonialInitiateRequestRepository extends MongoRepository<CustomerTestimonialInitiateRequest, String> {


    /**
     * This repository method is used to find CustomerTestimonialInitiateRequest by ProjectId and CustomerSubjectId
     * @param projectId
     * @param subjectId
     * @return
     */
    CustomerTestimonialInitiateRequest findFirstByProjectIdAndCustomerSubjectId(String projectId, Long subjectId);
}
