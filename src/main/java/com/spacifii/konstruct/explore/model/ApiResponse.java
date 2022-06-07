package com.spacifii.konstruct.explore.model;

import java.util.List;

public class ApiResponse<E> {
    private Integer responseCode;
    private String responseMessage;
    private E payload;
    private List<Error> errors;

    public ApiResponse(Integer responseCode, String message, E payload, List<Error> errors) {
        this.responseCode = responseCode;
        this.responseMessage = message;
        this.payload = payload;
        this.errors = errors;
    }

    public ApiResponse(APIResponseKey apiResponseKey, E payload, List<Error> errors) {
        this.responseCode = apiResponseKey.getCode();
        this.responseMessage = apiResponseKey.getMessage();
        this.payload = payload;
        this.errors = errors;
    }

    public ApiResponse() {
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public E getPayload() {
        return payload;
    }

    public void setPayload(E payload) {
        this.payload = payload;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }
}
