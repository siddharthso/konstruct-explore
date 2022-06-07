package com.spacifii.konstruct.explore.model;


public class Error {
    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Error(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Error() {
    }
}
