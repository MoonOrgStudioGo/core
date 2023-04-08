package com.example.dto

import com.us.base.library.entities.DtoWithStatusAndCode
import groovy.transform.CompileStatic
import groovy.transform.ToString
import io.micronaut.core.annotation.NonNull
import io.micronaut.serde.annotation.Serdeable

import javax.validation.constraints.NotBlank

@CompileStatic
@ToString
@Serdeable
class LanguageDto extends DtoWithStatusAndCode{

    @NonNull
    @NotBlank
    private String description

    String getDescription() {
        return description
    }

    void setDescription(String description) {
        this.description = description
    }
}
