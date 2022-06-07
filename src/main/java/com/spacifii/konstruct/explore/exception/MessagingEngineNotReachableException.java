package com.spacifii.konstruct.explore.exception;


import com.spacifii.konstruct.explore.model.APIResponseKey;

/**
 * his exception class is used when MessagingEngine is not reachable
 */
public class MessagingEngineNotReachableException extends RuntimeException implements ApiException {

    private  static final APIResponseKey purpose=APIResponseKey.MESSAGING_ENGINE_NOT_REACHABLE_EXCEPTION;

    @Override
    public APIResponseKey getApiResponseKey() {
        return purpose;
    }
}
