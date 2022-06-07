package com.spacifii.konstruct.explore.service.recentlyspacified;


import com.spacifii.konstruct.explore.entities.recentlyspacified.RecentlySpacifiedCustomerReview;
import com.spacifii.konstruct.explore.entities.recentlyspacified.SearchProfileBy;
import com.spacifii.konstruct.explore.model.dto.explore.FilterRequestDto;
import com.spacifii.konstruct.explore.model.dto.recentlyspacified.EnvelopedCustomerReview;
import com.spacifii.konstruct.explore.model.dto.recentlyspacified.ReviewAnswerDto;
import org.springframework.data.domain.Page;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/**
 * This service class manages RecentlySpacifiedCustomerReview
 */
public interface RecentlySpacifiedCustomerReviewService {


    /**
     * This service method is used to crete new RecentlySpacifiedCustomerReview
     * @param searchProfileBy
     * @param searchProfileValue
     * @param projectId
     * @param userType
     * @param subjectId
     * @return
     */
    RecentlySpacifiedCustomerReview createNewRecentlySpacifiedCustomerReview(SearchProfileBy searchProfileBy, String searchProfileValue, String projectId, String userType, Long subjectId);


    /**
     * This service method is used resend review communication
     * @param reviewId
     * @param subjectId
     * @return
     */
    RecentlySpacifiedCustomerReview resendReviewCommunication(String reviewId, Long subjectId);


    /**
     * This service method is used to answer Review
     * @param map
     * @param reviewId
     * @param subjectId
     * @return
     */
    RecentlySpacifiedCustomerReview answerReview(LinkedHashMap<String, Set<ReviewAnswerDto>> map, String reviewId, Long subjectId);

    /**
     * This service method is used to get RecentlySpacifiedCustomerReview via FilterRequestDto
     * @param filterRequestDto
     * @return
     */
    Page<RecentlySpacifiedCustomerReview> getRecentlySpacifiedCustomerReviewFiltered(FilterRequestDto filterRequestDto);

    /**
     * This service method is used to get user Initiated RecentlySpacifiedCustomerReview via FilterRequestDto
     * @param filterRequestDto
     * @param subjectId
     * @return
     */
    Page<RecentlySpacifiedCustomerReview> getMyInitiatedRecentlySpacifiedCustomerReviewFiltered(FilterRequestDto filterRequestDto, Long subjectId);

    /**
     * This service method is usd to get user Answered RecentlySpacifiedCustomerReview via FilterRequestDto
     * @param filterRequestDto
     * @param subjectId
     * @return
     */
    Page<RecentlySpacifiedCustomerReview> getMyAnsweredRecentlySpacifiedCustomerReviewFiltered(FilterRequestDto filterRequestDto, Long subjectId);


    /**
     * This service method is used to find RecentlySpacifiedCustomerReview by id
     * @param id
     * @return
     */
    RecentlySpacifiedCustomerReview findById(String id);

    /**
     * This service method is used to find EnvelopedCustomerReviewsById
     * @param id
     * @return
     */
    EnvelopedCustomerReview findEnvelopedCustomerReviewById(String id);

    /**
     * This service method is used to Find EnvelopedCustomerReview For the Project
     * @param projectId
     * @return
     */
    List<EnvelopedCustomerReview> findEnvelopedCustomerReviewForProject(String projectId);

    /**
     * This service method is used to find RecentlySpacified Customer Review By ProjectId
     * @param projectId
     * @return
     */
    List<RecentlySpacifiedCustomerReview> findByProjectId(String projectId);

}
