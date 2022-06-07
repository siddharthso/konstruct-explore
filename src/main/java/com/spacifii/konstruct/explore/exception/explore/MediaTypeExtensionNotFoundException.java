package com.spacifii.konstruct.explore.exception.explore;

import com.spacifii.konstruct.explore.exception.ApiException;
import com.spacifii.konstruct.explore.model.APIResponseKey;

public class MediaTypeExtensionNotFoundException extends RuntimeException implements ApiException {

    private static final APIResponseKey purpose = APIResponseKey.MEDIA_TYPE_EXTENSION_NOTFOUND;

    @Override
    public APIResponseKey getApiResponseKey() {
        return purpose;
    }
}

