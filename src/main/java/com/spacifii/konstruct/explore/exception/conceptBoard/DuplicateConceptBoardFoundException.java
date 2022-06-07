package com.spacifii.konstruct.explore.exception.conceptBoard;

import com.spacifii.konstruct.explore.exception.ApiException;
import com.spacifii.konstruct.explore.model.APIResponseKey;

public class DuplicateConceptBoardFoundException extends RuntimeException implements ApiException {

    private static final APIResponseKey purpose = APIResponseKey.DUPLICATE_CONCEPTBOARD_NOT_FOUND;

    @Override
    public APIResponseKey getApiResponseKey() {
        return purpose;
    }
}

