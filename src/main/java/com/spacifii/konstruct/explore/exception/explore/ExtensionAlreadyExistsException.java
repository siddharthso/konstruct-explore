package com.spacifii.konstruct.explore.exception.explore;

import com.spacifii.konstruct.explore.exception.ApiException;
import com.spacifii.konstruct.explore.model.APIResponseKey;

public class ExtensionAlreadyExistsException extends RuntimeException implements ApiException {

    private static final APIResponseKey purpose = APIResponseKey.EXTENSION_ALREADY_EXISTS;

    @Override
    public APIResponseKey getApiResponseKey() {
        return purpose;
    }
}

