package com.spacifii.konstruct.explore.exception.conceptBoard;

import com.spacifii.konstruct.explore.exception.ApiException;
import com.spacifii.konstruct.explore.model.APIResponseKey;

public class ConceptBoardMediaNotFoundException extends RuntimeException implements ApiException {

    private static final APIResponseKey purpose = APIResponseKey.CONCEPTBOARDMEDIA_NOT_FOUND;

    @Override
    public APIResponseKey getApiResponseKey() {
        return purpose;
    }
}

