package com.spacifii.konstruct.explore.exception.showcase;

import com.spacifii.konstruct.explore.exception.ApiException;
import com.spacifii.konstruct.explore.model.APIResponseKey;

/**
 * This exception is thrown when Material is not Found
 */
public class MaterialNotFoundException extends RuntimeException implements ApiException {

    private static final APIResponseKey purpose = APIResponseKey.MATERIAL_NOT_FOUND;

    @Override
    public APIResponseKey getApiResponseKey() {
        return purpose;
    }
}
