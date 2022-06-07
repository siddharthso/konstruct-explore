package com.spacifii.konstruct.explore.exception;


import com.spacifii.konstruct.explore.model.APIResponseKey;

/**
 * This exception class is used when MessagingEngine thrown some exception
 */
public class MessagingEngineException extends RuntimeException implements ApiException {

    private  static final APIResponseKey purpose=APIResponseKey.MESSAGING_ENGINE_EXCEPTION;

    @Override
    public APIResponseKey getApiResponseKey() {
        return purpose;
    }
}
