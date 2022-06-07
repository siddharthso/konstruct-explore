package com.spacifii.konstruct.explore.controller.recentlyspacified;


import com.spacifii.konstruct.explore.entities.recentlyspacified.UserTypeCustomerReviewQuestionMapping;
import com.spacifii.konstruct.explore.integration.rbac.annotation.RbacRegister;
import com.spacifii.konstruct.explore.model.APIResponseKey;
import com.spacifii.konstruct.explore.model.ApiResponse;
import com.spacifii.konstruct.explore.model.dto.recentlyspacified.UserTypeQuestionContainerRequestDto;
import com.spacifii.konstruct.explore.service.recentlyspacified.UserTypeCustomerReviewQuestionMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;
import java.util.List;

/**
 * This controller class manges UserTypeCustomerReviewQuestionMapping
 */
@RestController
@RequestMapping(value = "/api/userTypeCustomerReviewQuestionMapping")
public class UserTypeCustomerReviewQuestionMappingController {
    @Autowired
    UserTypeCustomerReviewQuestionMappingService userTypeCustomerReviewQuestionMappingService;


    /**
     * This controller method is used to add Questions to userTypeCustomerReviewQuestionMappingService
     * @param containers
     * @param userType
     * @param groupName
     * @param subjectid
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "addQuestionsToUserTypeCustomerReviewQuestionMapping")
    @PostMapping(value = "/userType/{userType}/group/{groupName}/addQuestionsToUserTypeCustomerReviewQuestionMapping", consumes = "application/json")
    public ApiResponse<UserTypeCustomerReviewQuestionMapping> addQuestionsToUserTypeCustomerReviewQuestionMapping(@RequestBody LinkedHashSet<UserTypeQuestionContainerRequestDto> containers,
                                                                                                                  @PathVariable("userType") String userType,
                                                                                                                  @PathVariable("groupName") String groupName,
                                                                                                                  @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,userTypeCustomerReviewQuestionMappingService.addQuestions(containers,groupName,userType,subjectid),null);
    }

    /**
     * This controller method is used to remove Questions to userTypeCustomerReviewQuestionMappingService
     * @param containers
     * @param userType
     * @param groupName
     * @param subjectid
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "removeQuestionsToUserTypeCustomerReviewQuestionMapping")
    @PostMapping(value = "/userType/{userType}/group/{groupName}/removeQuestionsToUserTypeCustomerReviewQuestionMapping", consumes = "application/json")
    public ApiResponse<UserTypeCustomerReviewQuestionMapping> removeQuestionsToUserTypeCustomerReviewQuestionMapping(@RequestBody LinkedHashSet<UserTypeQuestionContainerRequestDto> containers,
                                                                                                                  @PathVariable("userType") String userType,
                                                                                                                  @PathVariable("groupName") String groupName,
                                                                                                                  @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,userTypeCustomerReviewQuestionMappingService.removeQuestions(containers,groupName,userType,subjectid),null);
    }

    /**
     * This controller method is used to get userTypeCustomerReviewQuestionMappingService by userType
     * @param userType
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "getUserTypeCustomerReviewQuestionMappingByUserType", excluded = true)
    @GetMapping(value = "/userType/{userType}/getUserTypeCustomerReviewQuestionMappingByUserType")
    public ApiResponse<UserTypeCustomerReviewQuestionMapping> getUserTypeCustomerReviewQuestionMappingByUserType(@PathVariable("userType") String userType){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,userTypeCustomerReviewQuestionMappingService.findByUserType(userType),null);
    }


    /**
     * This controller method is used to get All userTypeCustomerReviewQuestionMappingService by userType
     *
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "getAllUserTypeCustomerReviewQuestionMapping", excluded = true)
    @GetMapping(value = "/getAllUserTypeCustomerReviewQuestionMapping")
    public ApiResponse<List<UserTypeCustomerReviewQuestionMapping>> getUserTypeCustomerReviewQuestionMappingByUserType(){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,userTypeCustomerReviewQuestionMappingService.findAll(),null);
    }
}
