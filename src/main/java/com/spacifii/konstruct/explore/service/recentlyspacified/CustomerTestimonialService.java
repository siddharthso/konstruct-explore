package com.spacifii.konstruct.explore.service.recentlyspacified;


import com.spacifii.konstruct.explore.entities.recentlyspacified.CustomerTestimonial;
import com.spacifii.konstruct.explore.entities.recentlyspacified.CustomerTestimonialInitiateRequest;
import com.spacifii.konstruct.explore.model.dto.explore.FilterRequestDto;
import com.spacifii.konstruct.explore.model.dto.recentlyspacified.EnvelopedCustomerTestimonial;
import com.spacifii.konstruct.explore.model.dto.recentlyspacified.TestimonialDto;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * This service class manages CustomerTestimonial
 */
public interface CustomerTestimonialService {


    /**
     * This service method is used to create CustomerTestimonial via CustomerTestimonialInitiateRequest
     * @param customerTestimonialInitiateRequest
     * @param subjectId
     * @return
     */
    CustomerTestimonial createCustomerTestimonialViaRequest(CustomerTestimonialInitiateRequest customerTestimonialInitiateRequest, Long subjectId);

    /**
     * This service method is used to create CustomerTestimonial directly by Customer
     * @param customerTestimonial
     * @param subjectId
     * @return
     */
    CustomerTestimonial createCustomerTestimonialB2CCustomer(CustomerTestimonial customerTestimonial, Long subjectId);


    /**
     * This service method is used to create Customer Response for Testimonial
     * @param testimonialDto
     * @param subjectId
     * @return
     */
    CustomerTestimonial createCustomerResponse(TestimonialDto testimonialDto, Long subjectId);


    /**
     * This service method is used to get CustomerTestimonial pages filterred
     * @param filterRequestDto
     * @return
     */
    Page<CustomerTestimonial> getCustomerTestimonialFilterred(FilterRequestDto filterRequestDto);

    /**
     * This service method is used to get my Written CustomerTestimonial
     * @param filterRequestDto
     * @param subjectId
     * @return
     */
    Page<CustomerTestimonial> getMyWrittenCustomerTestimonial(FilterRequestDto filterRequestDto, Long subjectId);

    /**
     * This service method is used to get CustomerTestimonial which are initiated by Me
     * @param filterRequestDto
     * @param subjectId
     * @return
     */
    Page<CustomerTestimonial> getCustomerTestimonialInitiatedByMe(FilterRequestDto filterRequestDto, Long subjectId);


    /**
     * This service method is usd to find CustomerTestimonial By Id
     * @param customerTestimonialId
     * @return
     */
    CustomerTestimonial findById(String customerTestimonialId);

    /**
     * This service method is used to find EnvelopedCustomerTestimonial by Id
     * @param customerTesimonialId
     * @return
     */
    EnvelopedCustomerTestimonial findEnvelopedCustomerTestimonialById(String customerTesimonialId);

    /**
     * This service method is used to find EnvelopedCustomerTestimonial filtered via filterRequestDto
     * @param filterRequestDto
     * @return
     */
    Page<EnvelopedCustomerTestimonial> findEnvelopedCustomerTestimonialFilterred(FilterRequestDto filterRequestDto);

    /**
     * This service method is used to find active EnvelopedCustomerTestimonial filtered via filterRequestDto
     * @param filterRequestDto
     * @return
     */
    Page<EnvelopedCustomerTestimonial> findActiveEnvelopedCustomerTestimonialFilterred(FilterRequestDto filterRequestDto);


    /**
     * This service method is used to find active EnvelopedCustomerTestimonial filtered via filterRequestDto
     * @param filterRequestDto
     * @return
     */
    Page<EnvelopedCustomerTestimonial> findActiveFeatureHomeEnvelopedCustomerTestimonialFilterred(FilterRequestDto filterRequestDto);

    /**
     * This service method is used to get EnvelopedCustomerTestimonial Via CustomerTestimonalPage
     * @param customerTestimonialPage
     * @return
     */
    Page<EnvelopedCustomerTestimonial> getEnvelopedCustomerTesimonialViaCustomerTestimonialPage(Page<CustomerTestimonial> customerTestimonialPage);

    /**
     * This service method is used to toggle Home
     * @param customerTestimonialId
     * @return
     */
    CustomerTestimonial toggleHome(String customerTestimonialId);

    /**
     * This service method is used to toggle Active
     * @param customerTestimonialId
     * @return
     */
    CustomerTestimonial toggleActive(String customerTestimonialId);

    /**
     * This service method is used to find Customer Testimonial by ProjectId
     * @param projectId
     * @return
     */
    List<CustomerTestimonial> findByProjectId(String projectId);

    /**
     * This service method is used to find EnvelopedCustomerTestimonial By ProjectId
     * @param projectId
     * @return
     */
    List<EnvelopedCustomerTestimonial> findEnvelopedCustomerTestimonialByProjectId(String projectId);





}
