package com.spacifii.konstruct.explore.exception.explore;

import com.spacifii.konstruct.explore.exception.ApiException;
import com.spacifii.konstruct.explore.model.APIResponseKey;

public class MemberServiceException extends RuntimeException implements ApiException {

    private static final APIResponseKey purpose = APIResponseKey.MEMBERSERVICE_EXCEPTION;

    @Override
    public APIResponseKey getApiResponseKey() {
        return purpose;
    }
}

