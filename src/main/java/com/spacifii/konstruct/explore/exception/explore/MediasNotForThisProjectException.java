package com.spacifii.konstruct.explore.exception.explore;

import com.spacifii.konstruct.explore.exception.ApiException;
import com.spacifii.konstruct.explore.model.APIResponseKey;

/**
 * This exception is thrown when User is asking for Image which is not mapped with Project
 */
public class MediasNotForThisProjectException extends RuntimeException implements ApiException {

    private static final APIResponseKey purpose = APIResponseKey.MEDIA_IS_NOT_FOR_THIS_PROJECT;

    @Override
    public APIResponseKey getApiResponseKey() {
        return purpose;
    }
}

