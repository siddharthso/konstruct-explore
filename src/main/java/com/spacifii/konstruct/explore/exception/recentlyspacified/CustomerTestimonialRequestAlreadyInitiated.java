package com.spacifii.konstruct.explore.exception.recentlyspacified;


import com.spacifii.konstruct.explore.exception.ApiException;
import com.spacifii.konstruct.explore.model.APIResponseKey;

/**
 * This Exception is Thrown when Testimonial Request is already Initiated
 */
public class CustomerTestimonialRequestAlreadyInitiated extends RuntimeException implements ApiException {

    private static final APIResponseKey purpose = APIResponseKey.CUSTOMER_TESTIMONIAL_REQUEST_ALREADY_THERE;

    @Override
    public APIResponseKey getApiResponseKey() {
        return purpose;
    }
}

