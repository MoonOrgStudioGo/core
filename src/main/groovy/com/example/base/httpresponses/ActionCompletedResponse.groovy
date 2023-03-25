package com.example.base.httpresponses

import com.example.base.entities.BasicDto
import com.example.base.entities.BasicEntity
import io.micronaut.serde.annotation.Serdeable

@Serdeable
class ActionCompletedResponse <T extends BasicDto> {

    ActionCompletedResponse(T result){
        this.result = result
    }

    ActionCompletedResponse(){
    }

    ActionCompletedResponse(Long id){
        this.id = id
    }

    Long id

    T result

    Boolean success = true

    T getResult() {
        return result
    }

    void setResult(T result) {
        this.result = result
    }

    Boolean isSuccess() {
        return success
    }

    void setSuccess(Boolean success) {
        this.success = success
    }
}
