package com.example.base.errors

import io.micronaut.serde.annotation.Serdeable

@Serdeable
class ErrorMessage {

    ErrorMessage(String message){
        this.message = message
    }

    private boolean success = false

    private String message

    String getMessage() {
        return message
    }

    void setMessage(String message) {
        this.message = message
    }

    boolean getSuccess() {
        return success
    }

    void setSuccess(boolean success) {
        this.success = success
    }
}