package com.spacifii.konstruct.explore.exception.explore;

import com.spacifii.konstruct.explore.exception.ApiException;
import com.spacifii.konstruct.explore.model.APIResponseKey;

public class ExploreWalkThroughNotFoundException extends RuntimeException implements ApiException {

    private static final APIResponseKey purpose = APIResponseKey.EXPLORE_WALKTHROUGH_NOT_FOUND;

    @Override
    public APIResponseKey getApiResponseKey() {
        return purpose;
    }
}

