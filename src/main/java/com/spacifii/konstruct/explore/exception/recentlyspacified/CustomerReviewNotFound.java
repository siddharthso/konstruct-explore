package com.spacifii.konstruct.explore.exception.recentlyspacified;




import com.spacifii.konstruct.explore.exception.ApiException;
import com.spacifii.konstruct.explore.model.APIResponseKey;

/**
 * This Exception is Thrown when Review Request is not found
 */
public class CustomerReviewNotFound extends RuntimeException implements ApiException {

    private static final APIResponseKey purpose = APIResponseKey.CUSTOMER_REVIEW_NOT_FOUND;

    @Override
    public APIResponseKey getApiResponseKey() {
        return purpose;
    }
}

