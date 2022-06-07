package com.spacifii.konstruct.explore.exception.showcase;

import com.spacifii.konstruct.explore.exception.ApiException;
import com.spacifii.konstruct.explore.model.APIResponseKey;

/**
 * This exception is thrown when Material is not Found
 */
public class ShowcaseNotFoundException extends RuntimeException implements ApiException {

    private static final APIResponseKey purpose = APIResponseKey.SHOW_CASE_NOT_FOUND;

    @Override
    public APIResponseKey getApiResponseKey() {
        return purpose;
    }
}
