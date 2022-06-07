package com.spacifii.konstruct.explore.controller.recentlyspacified;


import com.spacifii.konstruct.explore.entities.recentlyspacified.CustomerTestimonial;
import com.spacifii.konstruct.explore.integration.rbac.annotation.RbacRegister;
import com.spacifii.konstruct.explore.model.APIResponseKey;
import com.spacifii.konstruct.explore.model.ApiResponse;
import com.spacifii.konstruct.explore.model.dto.explore.FilterRequestDto;
import com.spacifii.konstruct.explore.model.dto.recentlyspacified.EnvelopedCustomerTestimonial;
import com.spacifii.konstruct.explore.model.dto.recentlyspacified.TestimonialDto;
import com.spacifii.konstruct.explore.service.recentlyspacified.CustomerTestimonialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller class manages CustomerTestimonial
 */
@RestController
@RequestMapping(value = "/api/customerTestimonial")
public class CustomerTestimonialController {

    @Autowired
    CustomerTestimonialService customerTestimonialService;


    /**
     * This controller method is used to add  createCustomerTestimonial via  B2C Customer directly
     * @param customerTestimonial
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "createCustomerTestimonialB2CCustomer")
    @PostMapping(value = "/createCustomerTestimonialB2CCustomer", consumes = "application/json")
    public ApiResponse<CustomerTestimonial> createCustomerTestimonialB2CCustomer(@RequestBody CustomerTestimonial customerTestimonial,
                                                                                 @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,customerTestimonialService.createCustomerTestimonialB2CCustomer(customerTestimonial,subjectid),null);
    }


    /**
     * This controller method is used to add  customer Response createCustomerTestimonial
     * @param testimonialDto
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "createCustomerTestimonialCustomerResponse")
    @PostMapping(value = "/createCustomerTestimonialCustomerResponse", consumes = "application/json")
    public ApiResponse<CustomerTestimonial> createCustomerTestimonialCustomerResponse(@RequestBody TestimonialDto testimonialDto,
                                                                                 @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,customerTestimonialService.createCustomerResponse(testimonialDto,subjectid),null);
    }

    /**
     * This controller method is used to get  createCustomerTestimonial with filter
     * @param filterRequestDto
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "getCustomerTestimonialFiltered")
    @PostMapping(value = "/getCustomerTestimonialFiltered", consumes = "application/json")
    public ApiResponse<Page<CustomerTestimonial>> getCustomerTestimonialFiltered(@RequestBody FilterRequestDto filterRequestDto,
                                                                                 @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,customerTestimonialService.getCustomerTestimonialFilterred(filterRequestDto),null);
    }

    /**
     * This controller method is used to get my written createCustomerTestimonial with filter
     * @param filterRequestDto
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "getMyWrittenCustomerTestimonial")
    @PostMapping(value = "/getMyWrittenCustomerTestimonial", consumes = "application/json")
    public ApiResponse<Page<CustomerTestimonial>> getMyWrittenCustomerTestimonial(@RequestBody FilterRequestDto filterRequestDto,
                                                                                  @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,customerTestimonialService.getMyWrittenCustomerTestimonial(filterRequestDto,subjectid),null);
    }


    /**
     * This controller method is used to get my initiated createCustomerTestimonial with filter
     * @param filterRequestDto
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "getCustomerTestimonialInitiatedByMe")
    @PostMapping(value = "/getCustomerTestimonialInitiatedByMe", consumes = "application/json")
    public ApiResponse<Page<CustomerTestimonial>> getCustomerTestimonialInitiatedByMe(@RequestBody FilterRequestDto filterRequestDto,
                                                                                      @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,customerTestimonialService.getCustomerTestimonialInitiatedByMe(filterRequestDto,subjectid),null);
    }


    /**
     * This controller method is used to get toggle Active inactive of CustomerTestimonial
     * @param customerTestimonialId
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "customerTestimonialToggleActive")
    @PostMapping(value = "/customerTestimonialToggleActive", consumes = "application/json")
    public ApiResponse<CustomerTestimonial> customerTestimonialToggleActive(@RequestBody String customerTestimonialId,
                                                                                      @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,customerTestimonialService.toggleActive(customerTestimonialId),null);
    }


    /**
     * This controller method is used to get toggle Home of CustomerTestimonial
     * @param customerTestimonialId
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "customerTestimonialToggleHome")
    @PostMapping(value = "/customerTestimonialToggleHome", consumes = "application/json")
    public ApiResponse<CustomerTestimonial> customerTestimonialToggleHome(@RequestBody String customerTestimonialId,
                                                                            @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,customerTestimonialService.toggleHome(customerTestimonialId),null);
    }


    /**
     * This controller method is used to get CustomerTestimonials for the Project
     * @param projectId
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "getCustomerTestimonialsForProject")
    @PostMapping(value = "/getCustomerTestimonialsForProject", consumes = "application/json")
    public ApiResponse<List<CustomerTestimonial>> getCustomerTestimonialsForProject(@RequestBody String projectId,
                                                                                @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,customerTestimonialService.findByProjectId(projectId),null);
    }

    /**
     * This controller method is used to get EnvelopedCustomerTestimonials for the Project
     * @param customerTestimonialId
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "getEnvelopedCustomerTestimonialById")
    @PostMapping(value = "/getEnvelopedCustomerTestimonialById", consumes = "application/json")
    public ApiResponse<EnvelopedCustomerTestimonial> getCustomerTestimonialsForId(@RequestBody String customerTestimonialId,
                                                                                       @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,customerTestimonialService.findEnvelopedCustomerTestimonialById(customerTestimonialId),null);
    }

    /**
     * This controller method is used to get EnvelopedCustomerTestimonials for the Project
     * @param projectId
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "getEnvelopedCustomerTestimonialByProjectId")
    @PostMapping(value = "/getEnvelopedCustomerTestimonialByProjectId", consumes = "application/json")
    public ApiResponse<List<EnvelopedCustomerTestimonial>> findEnvelopedCustomerTestimonialByProjectId(@RequestBody String projectId,
                                                                                  @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,customerTestimonialService.findEnvelopedCustomerTestimonialByProjectId(projectId),null);
    }



}
