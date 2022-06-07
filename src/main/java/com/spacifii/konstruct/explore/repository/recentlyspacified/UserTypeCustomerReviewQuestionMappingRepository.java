package com.spacifii.konstruct.explore.repository.recentlyspacified;


import com.spacifii.konstruct.explore.entities.recentlyspacified.UserTypeCustomerReviewQuestionMapping;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * This is DAO class for UserTypeCustomerReviewQuestionMapping
 */
@Repository
public interface UserTypeCustomerReviewQuestionMappingRepository extends MongoRepository<UserTypeCustomerReviewQuestionMapping,String> {
}
