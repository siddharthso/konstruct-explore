package com.spacifii.konstruct.explore.exception.explore;

import com.spacifii.konstruct.explore.exception.ApiException;
import com.spacifii.konstruct.explore.model.APIResponseKey;

public class KeywordNotFoundException extends RuntimeException implements ApiException {

    private static final APIResponseKey purpose = APIResponseKey.NO_KEYWORD_FOUND;

    @Override
    public APIResponseKey getApiResponseKey() {
        return purpose;
    }
}

