package com.spacifii.konstruct.explore.exception.showcase;


import com.spacifii.konstruct.explore.exception.ApiException;
import com.spacifii.konstruct.explore.model.APIResponseKey;

/**
 * This exception is thrown when ContainerMediaViewType is Not Found
 */
public class ContainerMediaViewTypeNotFoundException extends RuntimeException implements ApiException {

    private static final APIResponseKey purpose = APIResponseKey.CONTAINER_MEDIA_VIEW_TYPE_NOT_FOUND;

    @Override
    public APIResponseKey getApiResponseKey() {
        return purpose;
    }
}
