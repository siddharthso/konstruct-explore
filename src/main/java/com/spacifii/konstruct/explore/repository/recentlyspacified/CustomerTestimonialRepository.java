package com.spacifii.konstruct.explore.repository.recentlyspacified;


import com.spacifii.konstruct.explore.entities.recentlyspacified.CustomerTestimonial;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This is DAO class for CustomerTestimonial
 */
@Repository
public interface CustomerTestimonialRepository extends MongoRepository<CustomerTestimonial,String> {

    /**
     * This repository method is used to find CustomerTestimonial by ProjectId and Customer SubjectId
     * @param projectId
     * @param customerSubjectId
     * @return
     */
    CustomerTestimonial findFirstByProjectIdAndCustomerSubjectId(String projectId, Long customerSubjectId);

    /**
     * This repository method is used to find CustomerTestimonials by ProjectId
     * @param projectId
     * @return
     */
    List<CustomerTestimonial> findByProjectId(String projectId);
}
