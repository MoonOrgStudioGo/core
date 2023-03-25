package com.example.base.entities

import com.fasterxml.jackson.annotation.JsonFormat

abstract class BasicDto {

    String ENTITY_NAME
    String TABLE_NAME

    private Long id = -1L

    private boolean enabled = true

    boolean isEnabled() {
        return enabled
    }

    void setEnabled(boolean enabled) {
        this.enabled = enabled
    }

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    Date getInsertDate() {
        return insertDate
    }

    void setInsertDate(Date insertDate) {
        this.insertDate = insertDate
    }

    void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate
    }

    Date getLastUpdatedDate() {
        return lastUpdatedDate
    }

    //Audit camps
    protected Long version = 0L

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private Date insertDate

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private Date lastUpdatedDate

    abstract String obtainEntityName()
}