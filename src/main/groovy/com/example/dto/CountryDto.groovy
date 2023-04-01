package com.example.dto

import com.example.base.entities.DtoWithStatusAndCode
import groovy.transform.CompileStatic
import groovy.transform.ToString
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.Column

@CompileStatic
@ToString
@Serdeable
class CountryDto extends DtoWithStatusAndCode{

    @Column
    private String description

    private String currencyCode

    private String languageCode

    String getDescription() {
        return description
    }

    String getCurrencyCode() {
        return currencyCode
    }

    void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode
    }

    String getLanguageCode() {
        return languageCode
    }

    void setLanguageCode(String languageCode) {
        this.languageCode = languageCode
    }

    void setDescription(String description) {
        this.description = description
    }

}