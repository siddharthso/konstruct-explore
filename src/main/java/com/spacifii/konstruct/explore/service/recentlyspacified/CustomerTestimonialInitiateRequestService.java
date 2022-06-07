package com.spacifii.konstruct.explore.service.recentlyspacified;



import com.spacifii.konstruct.explore.entities.recentlyspacified.CustomerTestimonial;
import com.spacifii.konstruct.explore.entities.recentlyspacified.CustomerTestimonialInitiateRequest;
import com.spacifii.konstruct.explore.model.dto.explore.FilterRequestDto;
import org.springframework.data.domain.Page;

/**
 * This service class manages CustomerTestimonialInitiateRequest
 */
public interface CustomerTestimonialInitiateRequestService {

    /**
     * This service method is used to CustomerTestimonialInitiateRequest
     * @param customerTestimonialInitiateRequest
     * @param subject
     * @return
     */
    CustomerTestimonial saveCustomerTestimonialInitiateRequest(CustomerTestimonialInitiateRequest customerTestimonialInitiateRequest, Long subject);

    /**
     * This service method is used to Resend Customer Testimonial
     * @param customerTestimonialRequestId
     * @param subjectId
     * @return
     */
    CustomerTestimonial resentCustomerTestimonialInitiateRequest(String customerTestimonialRequestId, Long subjectId);


    /**
     * This service method is used to get CustomerTestimonialInitiateRequest via FilterRequestDto
     * @param filterRequestDto
     * @return
     */
    Page<CustomerTestimonialInitiateRequest> getCustomerTestimonialInitiateRequestFiltered(FilterRequestDto filterRequestDto);


    /**
     * This service method is used to get User's CustomerTestimonialInitiateRequest via FilterRequestDto
     * @param filterRequestDto
     * @return
     */
    Page<CustomerTestimonialInitiateRequest> getMyCustomerTestimonialInitiateRequestFiltered(FilterRequestDto filterRequestDto, Long subjectId);


    /**
     * This service method is used to get CustomerTestimonialInitiateRequest by Id
     * @param id
     * @return
     */
    CustomerTestimonialInitiateRequest findById(String id);

}
