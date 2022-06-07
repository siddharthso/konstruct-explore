package com.spacifii.konstruct.explore.exception.explore;

import com.spacifii.konstruct.explore.exception.ApiException;
import com.spacifii.konstruct.explore.model.APIResponseKey;

public class DuplicateMediaFoundException extends RuntimeException implements ApiException {

    private static final APIResponseKey purpose = APIResponseKey.DUPLICATE_MEDIA_FOUND;

    @Override
    public APIResponseKey getApiResponseKey() {
        return purpose;
    }
}

