package com.example.dto

import com.example.base.entities.DtoWithStatusAndCode
import groovy.transform.CompileStatic
import groovy.transform.ToString
import io.micronaut.serde.annotation.Serdeable

@CompileStatic
@ToString
@Serdeable
class UserDto extends DtoWithStatusAndCode{

    private String companyCode

    private String username

    private String password

    private String email

    private String telephoneNumber

    private String refreshToken

    private String insertRefreshToken

    private boolean resetPassword

    private String languageCode

    private String countryCode

    String getUsername() {
        return username
    }

    String getPassword() {
        return password
    }

    String getEmail() {
        return email
    }

    String getTelephoneNumber() {
        return telephoneNumber
    }

    String getRefreshToken() {
        return refreshToken
    }

    String getInsertRefreshToken() {
        return insertRefreshToken
    }

    boolean getResetPassword() {
        return resetPassword
    }

    static String getQUERY_LIST() {
        return QUERY_LIST
    }

    void setUsername(String username) {
        this.username = username
    }

    void setPassword(String password) {
        this.password = password
    }

    void setEmail(String email) {
        this.email = email
    }

    void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber
    }

    void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken
    }

    void setInsertRefreshToken(String insertRefreshToken) {
        this.insertRefreshToken = insertRefreshToken
    }

    void setResetPassword(boolean resetPassword) {
        this.resetPassword = resetPassword
    }

    String getLanguageCode() {
        return languageCode
    }

    void setLanguageCode(String languageCode) {
        this.languageCode = languageCode
    }

    String getCountryCode() {
        return countryCode
    }

    void setCountryCode(String countryCode) {
        this.countryCode = countryCode
    }

    String getCompanyCode() {
        return companyCode
    }

    void setCompanyCode(String companyCode) {
        this.companyCode = companyCode
    }
}
