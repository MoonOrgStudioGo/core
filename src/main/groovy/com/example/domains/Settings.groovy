package com.example.domains

import com.example.base.entities.BasicEntity
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.Column

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull


@MappedEntity("core_settings")
@Serdeable
class Settings extends BasicEntity {

    static final String TABLE_NAME = "core_settings"
    static final String ENTITY_NAME = "Settings"

    @Column
    @NotNull
    @NotBlank
    private String description

    @Column
    @NotNull
    @NotBlank
    private String value

    @Column
    @NotNull
    @NotBlank
    private String key

    @Override
    String toString() {

        return "Entity: SETTING - id = " + getId() + " code = " + getCode() + " description = " + getDescription() + " insertDate =  " + getInsertDate() + " lastUpdatedDate = " + getLastUpdatedDate()
    }

    // GETTERS AND SETTERS
    static String getTableName() {
        return TABLE_NAME
    }

    static String getEntityName() {
        return ENTITY_NAME
    }

    String getValue() {
        return value
    }

    void setValue(String value) {
        this.value = value
    }

    String getKey() {
        return key
    }

    void setKey(String key) {
        this.key = key
    }

    String getDescription() {
        return description
    }

    void setDescription(String description) {
        this.description = description
    }
}
