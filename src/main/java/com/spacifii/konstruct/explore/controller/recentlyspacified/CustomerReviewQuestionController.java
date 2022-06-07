package com.spacifii.konstruct.explore.controller.recentlyspacified;


import com.spacifii.konstruct.explore.entities.recentlyspacified.CustomerReviewQuestion;
import com.spacifii.konstruct.explore.integration.rbac.annotation.RbacRegister;
import com.spacifii.konstruct.explore.model.APIResponseKey;
import com.spacifii.konstruct.explore.model.ApiResponse;
import com.spacifii.konstruct.explore.model.dto.explore.FilterRequestDto;
import com.spacifii.konstruct.explore.service.recentlyspacified.CustomerReviewQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * This controller class manages CustomerReviewQuestion
 */
@RequestMapping(value = "/api/")
@RestController
public class CustomerReviewQuestionController {

    @Autowired
    CustomerReviewQuestionService customerReviewQuestionService;


    /**
     * This controller method is used to add or update CustomerReviewQuestion
     * @param customerReviewQuestion
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "saveOrUpdateCustomerReviewQuestion")
    @PostMapping(value = "/saveOrUpdateCustomerReviewQuestion", consumes = "application/json")
    public ApiResponse<CustomerReviewQuestion> saveOrUpdateCustomerQuestion(@RequestBody CustomerReviewQuestion customerReviewQuestion,
                                                                            @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,customerReviewQuestionService.saveOrUpdateCustomerQuestion(customerReviewQuestion,subjectid),null);
    }



    /**
     * This controller method is used get CustomerReviewQuestion pages via FilterRequestDto
     * @param filterRequestDto
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "getCustomerReviewQuestionFiltered")
    @PostMapping(value = "/getCustomerReviewQuestionFiltered", consumes = "application/json")
    public ApiResponse<Page<CustomerReviewQuestion>> getCustomerReviewQuestionFiltered(@RequestBody FilterRequestDto filterRequestDto,
                                                                                       @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,customerReviewQuestionService.getCustomerReviewQuestionFiltered(filterRequestDto),null);
    }

    /**
     * This controller method is used get CustomerReviewQuestion by id
     * @param customerReviewQuestionId
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "getCustomerReviewQuestionById", excluded = true)
    @GetMapping(value = "/{customerReviewQuestionId}/getCustomerReviewQuestionById")
    public ApiResponse<CustomerReviewQuestion> getCustomerReviewQuestionFiltered(@PathVariable("customerReviewQuestionId") String customerReviewQuestionId){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,customerReviewQuestionService.getCustomerReviewQuestionById(customerReviewQuestionId),null);
    }






}
