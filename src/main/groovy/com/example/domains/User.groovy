package com.example.domains

import com.example.base.entities.BasicEntity
import groovy.transform.CompileStatic
import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.annotation.Relation
import io.micronaut.data.annotation.event.PrePersist
import io.micronaut.data.annotation.event.PreUpdate
import io.micronaut.serde.annotation.Serdeable

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.sql.Timestamp

@CompileStatic
@MappedEntity("core_user")
@Serdeable
@Introspected
class User extends BasicEntity{

    public static final String TABLE_NAME = "core_user"
    public static final String ENTITY_NAME = "User"

    private Company company

    private String username

    private String password

    private String email

    private String telephoneNumber

    private String refreshToken

    private Timestamp insertRefreshToken

    private boolean resetPassword = false

    @Relation(Relation.Kind.ONE_TO_ONE)
    private Language language

    @Relation(Relation.Kind.ONE_TO_ONE)
    private Country country

    @Override
    String toString(){

        return "Entity: USER - id = " + getId() + " code: " + getCode() + " insertDate =  " + getInsertDate() + " lastUpdatedDate = " + getLastUpdatedDate() + " username = " + getUsername()
    }

    static String getTABLE_NAME() {
        return TABLE_NAME
    }

    static String getENTITY_NAME() {
        return ENTITY_NAME
    }

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

    Timestamp getInsertRefreshToken() {
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

    void setInsertRefreshToken(Timestamp insertRefreshToken) {
        this.insertRefreshToken = insertRefreshToken
    }

    void setResetPassword(boolean resetPassword) {
        this.resetPassword = resetPassword
    }

    Language getLanguage() {
        return language;
    }

    void setLanguage(Language language) {
        this.language = language
    }

    Country getCountry() {
        return country
    }

    void setCountry(Country country) {
        this.country = country
    }

    Company getCompany() {
        return company
    }

    void setCompany(Company company) {
        this.company = company
    }

    static Map getQueries() {
        return queries
    }
    public static final String QUERY_LIST = """
                                                SELECT cu.id as id,
                                                        cu.code as code
                                                FROM core_user cu
                                            """


    static final Map queries = [list: QUERY_LIST]

    @PreUpdate
    void hashPasswordPreUpdate(){

        if (resetPassword) {
            String salt = "asdasdasd"

            try {
                MessageDigest md = MessageDigest.getInstance("SHA-512")
                md.update(salt.getBytes())
                byte[] bytes = md.digest(password.getBytes())
                StringBuilder sb = new StringBuilder()
                for (byte aByte : bytes) {
                    sb.append(Integer.toString((aByte & 0xff) + 0x100, 16)
                            .substring(1))
                }
                password = sb.toString()
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace()
            }
            resetPassword = false
        }
    }

    @PrePersist
    void hashPasswordPrePersist(){

        String salt = "asdasdasd"

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512")
            md.update(salt.getBytes())
            byte[] bytes = md.digest(password.getBytes())
            StringBuilder sb = new StringBuilder()
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16)
                        .substring(1))
            }
            password = sb.toString()
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace()
        }
    }
}
