package com.example.domains

import com.example.base.entities.BasicEntity
import io.micronaut.core.annotation.Introspected
import io.micronaut.core.annotation.NonNull
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.annotation.event.PrePersist
import io.micronaut.data.annotation.event.PreUpdate
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.Column
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne

import javax.validation.constraints.NotBlank
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

@MappedEntity("core_user")
@Serdeable
@Introspected
class User extends BasicEntity{

    public static final String TABLE_NAME = "core_user"
    public static final String ENTITY_NAME = "User"

    //@OneToOne
    //@JoinColumn(name = "company_id", referencedColumnName = "id")
    //private Company company

    @Column
    @NonNull
    @NotBlank
    private String username

    @Column
    @NonNull
    @NotBlank
    private String password

    @Column
    @NonNull
    @NotBlank
    private String email

    @Column
    @NonNull
    @NotBlank
    private String telephoneNumber

    @Column
    private String refreshToken

    @Column
    private Date insertRefreshToken

    @Column
    private boolean resetPassword = false

    @OneToOne
    @JoinColumn(name = "language_id", referencedColumnName = "id")
    private Language language

    // @OneToOne
    // @JoinColumn(name = "country_id", referencedColumnName = "id")
    // private Country country

    @Override
    public String toString(){

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

    Date getInsertRefreshToken() {
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

    void setInsertRefreshToken(Date insertRefreshToken) {
        this.insertRefreshToken = insertRefreshToken
    }

    void setResetPassword(boolean resetPassword) {
        this.resetPassword = resetPassword
    }

    Language getLanguage() {
        return language
    }

    void setLanguage(Language language) {
        this.language = language
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

    void hidPassword(){
        this.password = null
    }

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
