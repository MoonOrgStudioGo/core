package com.example.dto

import com.us.base.library.entities.DtoWithStatusAndCode
import com.example.domains.Country
import groovy.transform.CompileStatic
import groovy.transform.ToString
import io.micronaut.serde.annotation.Serdeable

@CompileStatic
@ToString
@Serdeable
class CompanyDto extends DtoWithStatusAndCode{

    private String description

    private String vatNumber

    private String businessName

    private String address

    private String email

    private String phoneNumber

    private String countryCode

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

    String getCountryCode() {
        return countryCode
    }

    void setCountryCode(String countryCode) {
        this.countryCode = countryCode
    }
}
