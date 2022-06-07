package com.spacifii.konstruct.explore.exception.conceptBoard;

import com.spacifii.konstruct.explore.exception.ApiException;
import com.spacifii.konstruct.explore.model.APIResponseKey;

public class ConceptBoardMediaDuplicateForSameSubjectException extends RuntimeException implements ApiException {

    private static final APIResponseKey purpose = APIResponseKey.CONCEPTBOARDMEIDA_DUPLICATE_FOUND;

    private String fileaname;

    public ConceptBoardMediaDuplicateForSameSubjectException(String fileaname) {
        this.fileaname = fileaname;
    }

    public String getFileaname() {
        return fileaname;
    }

    @Override
    public APIResponseKey getApiResponseKey() {
        return purpose;
    }
}
