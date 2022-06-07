package com.spacifii.konstruct.explore.exception;


import com.spacifii.konstruct.explore.model.APIResponseKey;

public class UserProfileNotFoundException extends RuntimeException implements ApiException {

    private static final APIResponseKey purpose = APIResponseKey.USER_PROFILE_NOT_REACHABLE;

    @Override
    public APIResponseKey getApiResponseKey() {
        return purpose;
    }
}

