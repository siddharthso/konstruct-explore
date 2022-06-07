package com.spacifii.konstruct.explore.controller.recentlyspacified;


import com.spacifii.konstruct.explore.entities.recentlyspacified.CustomerTestimonial;
import com.spacifii.konstruct.explore.entities.recentlyspacified.CustomerTestimonialInitiateRequest;
import com.spacifii.konstruct.explore.integration.rbac.annotation.RbacRegister;
import com.spacifii.konstruct.explore.model.APIResponseKey;
import com.spacifii.konstruct.explore.model.ApiResponse;
import com.spacifii.konstruct.explore.model.dto.explore.FilterRequestDto;
import com.spacifii.konstruct.explore.service.recentlyspacified.CustomerTestimonialInitiateRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * This controller class manages CustomerTestimonialInitiateRequest
 */
@RestController
@RequestMapping(value = "/api/customerTestimonialInitiateRequest")
public class CustomerTestimonialInitiateRequestController {

    @Autowired
    CustomerTestimonialInitiateRequestService customerTestimonialInitiateRequestService;


    /**
     * This controller method is used to add  CustomerTestimonialInitiateRequest
     * @param testimonialInitiateRequest
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "saveCustomerTestimonialInitiateRequest")
    @PostMapping(value = "/saveCustomerTestimonialInitiateRequest", consumes = "application/json")
    public ApiResponse<CustomerTestimonial> saveCustomerTestimonialInitiateRequest(@RequestBody CustomerTestimonialInitiateRequest testimonialInitiateRequest,
                                                                                   @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,customerTestimonialInitiateRequestService.saveCustomerTestimonialInitiateRequest(testimonialInitiateRequest,subjectid),null);
    }

    /**
     * This controller method is used to resend CustomerTestimonialInitiateRequest communication
     * @param testimonialInitiateRequestId
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "resentCustomerTestimonialInitiateRequest")
    @PostMapping(value = "/resentCustomerTestimonialInitiateRequest", consumes = "application/json")
    public ApiResponse<CustomerTestimonial> saveCustomerTestimonialInitiateRequest(@RequestBody String testimonialInitiateRequestId,
                                                                                   @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,customerTestimonialInitiateRequestService.resentCustomerTestimonialInitiateRequest(testimonialInitiateRequestId,subjectid),null);
    }

    /**
     * This controller method is used to get Pages of CustomerTestimonialInitiateRequest via FilterRequest DTO
     * @param filterRequestDto
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "getCustomerTestimonialInitiateRequestFiltered")
    @PostMapping(value = "/getCustomerTestimonialInitiateRequestFiltered", consumes = "application/json")
    public ApiResponse<Page<CustomerTestimonialInitiateRequest>> getCustomerTestimonialInitiateRequestFiltered(@RequestBody FilterRequestDto filterRequestDto,
                                                                                                               @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,customerTestimonialInitiateRequestService.getCustomerTestimonialInitiateRequestFiltered(filterRequestDto),null);
    }


    /**
     * This controller method is used to get Pages of CustomerTestimonialInitiateRequest via FilterRequest DTO
     * @param filterRequestDto
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "getMyCustomerTestimonialInitiateRequestFiltered")
    @PostMapping(value = "/getMyCustomerTestimonialInitiateRequestFiltered", consumes = "application/json")
    public ApiResponse<Page<CustomerTestimonialInitiateRequest>> getMyCustomerTestimonialInitiateRequestFiltered(@RequestBody FilterRequestDto filterRequestDto,
                                                                                                                 @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,customerTestimonialInitiateRequestService.getMyCustomerTestimonialInitiateRequestFiltered(filterRequestDto,subjectid),null);
    }

    /**
     * This controller method is used to get  CustomerTestimonialInitiateRequest by Id
     * @param customerTestimonialInitiateRequestId
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "getCustomerTestimonialInitiateRequestById")
    @GetMapping(value = "{customerTestimonialInitiateRequestId}/getCustomerTestimonialInitiateRequestById")
    public ApiResponse<CustomerTestimonialInitiateRequest> getCustomerTestimonialInitiateRequestFiltered(@PathVariable("customerTestimonialInitiateRequestId") String customerTestimonialInitiateRequestId,
                                                                                                               @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,customerTestimonialInitiateRequestService.findById(customerTestimonialInitiateRequestId),null);
    }

}
