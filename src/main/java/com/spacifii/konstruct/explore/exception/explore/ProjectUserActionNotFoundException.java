package com.spacifii.konstruct.explore.exception.explore;

import com.spacifii.konstruct.explore.exception.ApiException;
import com.spacifii.konstruct.explore.model.APIResponseKey;

/**
 * This Exception is thrown when Project UserAction is not Found
 */
public class ProjectUserActionNotFoundException extends RuntimeException implements ApiException {

    private static final APIResponseKey purpose = APIResponseKey.PROJECT_USER_ACTION_NOT_FOUND;

    @Override
    public APIResponseKey getApiResponseKey() {
        return purpose;
    }
}

