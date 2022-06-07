package com.spacifii.konstruct.explore.exception.conceptBoard;

import com.spacifii.konstruct.explore.exception.ApiException;
import com.spacifii.konstruct.explore.model.APIResponseKey;

/**
 * This exception is thrown when Media UserAction is not found
 */
public class ConceptBoardMediaUserActionNotFoundException extends RuntimeException implements ApiException {

    private static final APIResponseKey purpose = APIResponseKey.CONCEPTBOARD_MEDIA_USER_ACTION_NOT_FOUND;

    @Override
    public APIResponseKey getApiResponseKey() {
        return purpose;
    }
}

