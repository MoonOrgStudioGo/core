package com.example.domains

import com.example.base.entities.BasicEntity
import groovy.transform.CompileStatic
import groovy.transform.ToString
import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.Column

@CompileStatic
@ToString
@MappedEntity("core_country")
@Introspected
@Serdeable
class Country extends BasicEntity{

    static final String TABLE_NAME = "core_country"
    static final String ENTITY_NAME = "Country"

    @Column
    private String description

    private Currency currency

    private Language language

    @Override
    String toString(){

        return "Entity: COUNTRY - id: " + getId() + " description: " + getDescription() + " code: " + getCode() + " lastUpdatedDate: " + getLastUpdatedDate() + " insertDate: " + getInsertDate()
    }

    Language getLanguage() {
        return language
    }

    void setLanguage(Language language) {
        this.language = language
    }

    Currency getCurrency() {
        return currency
    }

    void setCurrency(Currency currency) {
        this.currency = currency
    }

    String getDescription() {
        return description
    }

    void setDescription(String description) {
        this.description = description
    }

    //GETTER AND SETTER
    static String getTableName(){
        return TABLE_NAME
    }

    static String getEntityName(){
        return ENTITY_NAME
    }
}