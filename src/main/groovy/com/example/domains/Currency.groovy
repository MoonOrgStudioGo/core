package com.example.domains

import com.example.base.entities.BasicEntity
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable

@MappedEntity("core_currency")
@Serdeable
 class Currency extends BasicEntity{

     static final String TABLE_NAME = "core_currency"
     static final String ENTITY_NAME = "Currency"

    private String description

    @Override
    String toString(){

        return "Entity: CURRENCY - id: " + getId() + " description: " + getDescription() + " code: " + getCode() + " lastUpdatedDate: " + getLastUpdatedDate() + " insertDate: " + getInsertDate()
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