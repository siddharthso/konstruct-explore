package com.spacifii.konstruct.explore.service.recentlyspacified;



import com.spacifii.konstruct.explore.entities.recentlyspacified.UserTypeCustomerReviewQuestionMapping;
import com.spacifii.konstruct.explore.model.dto.recentlyspacified.UserTypeQuestionContainerRequestDto;

import java.util.LinkedHashSet;
import java.util.List;

/**
 * This service class manages UserTypeQuestionContainer
 */
public interface UserTypeCustomerReviewQuestionMappingService {


    /**
     * This service method is used to add Questions to UserTypeCustomerReviewQuestionMapping
     * @param containers
     * @param groupName
     * @param userType
     * @param subjectId
     * @return
     */
    UserTypeCustomerReviewQuestionMapping addQuestions(LinkedHashSet<UserTypeQuestionContainerRequestDto> containers, String groupName, String userType, Long subjectId);

    /**
     * This service method is used to remove Questions to UserTypeCustomerReviewQuestionMapping
     * @param containers
     * @param groupName
     * @param userType
     * @param subjectId
     * @return
     */
    UserTypeCustomerReviewQuestionMapping removeQuestions(LinkedHashSet<UserTypeQuestionContainerRequestDto> containers, String groupName, String userType, Long subjectId);

    /**
     * This service method is used to find UserTypeCustomerReviewQuestionMapping by UserType
     * @param userType
     * @return
     */
    UserTypeCustomerReviewQuestionMapping findByUserType(String userType);

    /**
     * This service method is used to all UserTypeCustomerReviewQuestionMapping
     * @return
     */
    List<UserTypeCustomerReviewQuestionMapping> findAll();

}
