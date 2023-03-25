package com.example.domains

import com.example.base.entities.BasicEntity
import io.micronaut.core.annotation.Introspected
import io.micronaut.core.annotation.NonNull
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.Column

import javax.validation.constraints.NotBlank


@MappedEntity("core_language")
@Serdeable
class Language extends BasicEntity{

    static final String TABLE_NAME = "core_language"
    static final String ENTITY_NAME = "Language"

    @Column
    @NonNull
    @NotBlank
    private String description

    @Override
    String toString(){

        return "Entity: LANGUAGE - id: " + getId() + " description: " + getDescription() + " code: " + getCode() + " lastUpdatedDate: " + getLastUpdatedDate() + " insertDate: " + getInsertDate()
    }

    Language(){
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
