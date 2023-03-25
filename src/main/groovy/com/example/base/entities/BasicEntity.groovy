package com.example.base.entities

import io.micronaut.core.annotation.NonNull
import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.DateUpdated
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.Version
import io.micronaut.data.annotation.event.PrePersist
import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass

import javax.validation.constraints.NotBlank
import java.sql.Timestamp

@MappedSuperclass
abstract class BasicEntity {

    static String TABLE_NAME
    static String ENTITY_NAME

    @Id
    @GeneratedValue
    private Long id

    @Column
    @NonNull
    @NotBlank
    private String code

    @Column
    private int status = 0

    @Version
    protected Long version

    @DateCreated
    private Timestamp insertDate

    @DateUpdated
    private Timestamp lastUpdatedDate

    @Column(name = "flag_enabled")
    private boolean enabled = true

    boolean isEnabled() {
        return enabled
    }

    void setEnabled(boolean flagEnabled) {
        this.enabled = flagEnabled
    }

    Timestamp getInsertDate() {
        return insertDate
    }

    void setInsertDate(Timestamp insertDate) {
        this.insertDate = insertDate
    }

    void setLastUpdatedDate(Timestamp lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate
    }

    Timestamp getLastUpdatedDate() {
        return lastUpdatedDate
    }

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
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

    Long getVersion() {
        return version
    }

    void setVersion(Long version) {
        this.version = version
    }
}
