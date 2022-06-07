package com.spacifii.konstruct.explore.controller.recentlyspacified;


import com.spacifii.konstruct.explore.entities.recentlyspacified.RecentlySpacifiedCustomerReview;
import com.spacifii.konstruct.explore.integration.rbac.annotation.RbacRegister;
import com.spacifii.konstruct.explore.model.APIResponseKey;
import com.spacifii.konstruct.explore.model.ApiResponse;
import com.spacifii.konstruct.explore.model.dto.explore.FilterRequestDto;
import com.spacifii.konstruct.explore.model.dto.recentlyspacified.CreateNewRecentlySpacifiedCustomerReviewDto;
import com.spacifii.konstruct.explore.model.dto.recentlyspacified.EnvelopedCustomerReview;
import com.spacifii.konstruct.explore.model.dto.recentlyspacified.ReviewAnswerDto;
import com.spacifii.konstruct.explore.service.recentlyspacified.RecentlySpacifiedCustomerReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/**
 * This controller class manges RecentlySpacifiedCustomerReview
 */
@RestController
@RequestMapping(value = "/api/recentlySpacifiedCustomerReview")
public class RecentlySpacifiedCustomerReviewController {

    @Autowired
    RecentlySpacifiedCustomerReviewService recentlySpacifiedCustomerReviewService;



    /**
     * This controller method is used to add  CustomerTestimonialInitiateRequest
     * @param createNewRecentlySpacifiedCustomerReviewDto
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "createNewRecentlySpacifiedCustomerReview")
    @PostMapping(value = "/createNewRecentlySpacifiedCustomerReview", consumes = "application/json")
    public ApiResponse<RecentlySpacifiedCustomerReview> saveCustomerTestimonialInitiateRequest(@RequestBody CreateNewRecentlySpacifiedCustomerReviewDto createNewRecentlySpacifiedCustomerReviewDto,
                                                                                               @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,
                recentlySpacifiedCustomerReviewService.createNewRecentlySpacifiedCustomerReview
                        (createNewRecentlySpacifiedCustomerReviewDto.getSearchProfileBy(),
                                createNewRecentlySpacifiedCustomerReviewDto.getSearchProfileValue(),
                                createNewRecentlySpacifiedCustomerReviewDto.getProjectId(),
                                createNewRecentlySpacifiedCustomerReviewDto.getUserType(),subjectid),null);
    }



    /**
     * This controller method is used to resend Communication  CustomerTestimonialInitiateRequest
     * @param reviewId
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "resendReviewCommunicationRecentlySpacifiedCustomerReview")
    @PostMapping(value = "/resendReviewCommunicationRecentlySpacifiedCustomerReview", consumes = "application/json")
    public ApiResponse<RecentlySpacifiedCustomerReview> resendReviewCommunication(@RequestBody String  reviewId,
                                                                                               @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,
                recentlySpacifiedCustomerReviewService.resendReviewCommunication(reviewId,subjectid),null);
    }


    /**
     * This controller method is used to Answer CustomerTestimonialInitiateRequest
     * @param map
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "answerCustomerTestimonialInitiateRequest")
    @PostMapping(value = "/{reviewId}/answerCustomerTestimonialInitiateRequest", consumes = "application/json")
    public ApiResponse<RecentlySpacifiedCustomerReview> answerCustomerTestimonialInitiateRequest(@RequestBody LinkedHashMap<String,Set<ReviewAnswerDto>> map,
                                                                                                     @PathVariable("reviewId") String  reviewId,
                                                                                                     @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,
                recentlySpacifiedCustomerReviewService.answerReview(map,reviewId,subjectid),null);
    }

    /**
     * This controller method is used to get CustomerTestimonialInitiateRequest pages by FilteredRequestDto
     * @param filterRequestDto
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "getRecentlySpacifiedCustomerReviewFiltered")
    @PostMapping(value = "/getRecentlySpacifiedCustomerReviewFiltered", consumes = "application/json")
    public ApiResponse<Page<RecentlySpacifiedCustomerReview>> saveCustomerTestimonialInitiateRequest(@RequestBody FilterRequestDto filterRequestDto,
                                                                                                     @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,
                recentlySpacifiedCustomerReviewService.getRecentlySpacifiedCustomerReviewFiltered(filterRequestDto),null);
    }



    /**
     * This controller method is used to get CustomerTestimonialInitiateRequest pages by FilteredRequestDto
     * @param filterRequestDto
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "getMyInitiatedRecentlySpacifiedCustomerReviewFiltered")
    @PostMapping(value = "/getMyInitiatedRecentlySpacifiedCustomerReviewFiltered", consumes = "application/json")
    public ApiResponse<Page<RecentlySpacifiedCustomerReview>> getMyInitiatedRecentlySpacifiedCustomerReviewFiltered(@RequestBody FilterRequestDto filterRequestDto,
                                                                                                                    @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,
                recentlySpacifiedCustomerReviewService.getMyInitiatedRecentlySpacifiedCustomerReviewFiltered(filterRequestDto,subjectid),null);
    }


    /**
     * This controller method is used to get CustomerTestimonialInitiateRequest pages by FilteredRequestDto
     * @param filterRequestDto
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "getMyAnsweredRecentlySpacifiedCustomerReviewFiltered")
    @PostMapping(value = "/getMyAnsweredRecentlySpacifiedCustomerReviewFiltered", consumes = "application/json")
    public ApiResponse<Page<RecentlySpacifiedCustomerReview>> getMyAnsweredRecentlySpacifiedCustomerReviewFiltered(@RequestBody FilterRequestDto filterRequestDto,
                                                                                                                   @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,
                recentlySpacifiedCustomerReviewService.getMyAnsweredRecentlySpacifiedCustomerReviewFiltered(filterRequestDto,subjectid),null);
    }



    /**
     * This controller method is used to resend Communication  CustomerTestimonialInitiateRequest
     * @param reviewId
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "getRecentlySpacifiedCustomerReviewById")
    @GetMapping(value = "/{reviewId}/getRecentlySpacifiedCustomerReviewById")
    public ApiResponse<RecentlySpacifiedCustomerReview> findById(@PathVariable("reviewId") String  reviewId,
                                                                 @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,
                recentlySpacifiedCustomerReviewService.findById(reviewId),null);
    }


    /**
     * This controller method is used to resend Communication  CustomerTestimonialInitiateRequest
     * @param reviewId
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "getEnvelopedRecentlySpacifiedCustomerReviewById")
    @GetMapping(value = "/{reviewId}/getEnvelopedRecentlySpacifiedCustomerReviewById")
    public ApiResponse<EnvelopedCustomerReview> findEnvelopedCustomerReviewById(@PathVariable("reviewId") String  reviewId,
                                                         @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,
                recentlySpacifiedCustomerReviewService.findEnvelopedCustomerReviewById(reviewId),null);
    }


    /**
     * This controller method is used to resend Communication  CustomerTestimonialInitiateRequest
     * @param projectId
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "findEnvelopedCustomerReviewByProjectId")
    @GetMapping(value = "/{projectId}/findEnvelopedCustomerReviewByProjectId")
    public ApiResponse<List<EnvelopedCustomerReview>> findEnvelopedCustomerReviewByProjectId(@PathVariable("projectId") String  projectId,
                                                                                             @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,
                recentlySpacifiedCustomerReviewService.findEnvelopedCustomerReviewForProject(projectId),null);
    }

}
