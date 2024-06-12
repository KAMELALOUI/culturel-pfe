package com.kamel.media.dto;




public class AuthCheckResponseObject {

    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


    public AuthCheckResponseObject() {
        super();
    }


    public AuthCheckResponseObject(boolean success) {
        super();
        this.success = success;
    }


}