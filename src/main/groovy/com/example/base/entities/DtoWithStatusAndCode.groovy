package com.example.base.entities

import io.micronaut.core.annotation.Introspected

@Introspected
abstract class DtoWithStatusAndCode extends  BasicDto{

    private String code

    private String statusDescription

    private int status

    String getStatusDescription() {
        return statusDescription
    }

    void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription
    }

    String getCode() {
        return code
    }

    void setCode(String code) {
        this.code = code
    }

    int getStatus() {
        return status
    }

    void setStatus(int status) {
        this.status = status
    }

}
