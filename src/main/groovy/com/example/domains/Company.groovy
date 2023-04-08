package com.example.domains

import com.us.base.library.entities.BasicEntity
import groovy.transform.CompileStatic
import groovy.transform.ToString
import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable


@CompileStatic
@ToString
@MappedEntity("core_company")
@Introspected
@Serdeable
class Company extends BasicEntity{

    static final String TABLE_NAME = "core_company"
    static final String ENTITY_NAME = "Company"

    private String description

    private String vatNumber

    private String businessName

    private String address

    private String email

    private String phoneNumber

    private Country country

    @Override
    String toString(){

        return "Entity: COMPANY - id = " + getId() + " code = " + getCode() + " description = " + getDescription() + " insertDate =  " + getInsertDate() + " lastUpdatedDate = " + getLastUpdatedDate()
    }

    Company(){}

    // GETTERS AND SETTERS
    static String getTableName(){
        return TABLE_NAME
    }

    static String getEntityName(){
        return ENTITY_NAME
    }

    String getDescription() {
        return description
    }

    void setDescription(String description) {
        this.description = description
    }

    String getPhoneNumber() {
        return phoneNumber
    }

    void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber
    }

    Country getCountry() {
        return country
    }

    void setCountry(Country country) {
        this.country = country
    }

    String getVatNumber() {
        return vatNumber
    }

    void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber
    }

    String getBusinessName() {
        return businessName
    }

    void setBusinessName(String businessName) {
        this.businessName = businessName
    }

    String getAddress() {
        return address
    }

    void setAddress(String address) {
        this.address = address
    }

    String getEmail() {
        return email
    }

    void setEmail(String email) {
        this.email = email
    }
}
