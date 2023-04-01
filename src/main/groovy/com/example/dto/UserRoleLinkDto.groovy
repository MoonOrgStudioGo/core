package com.example.dto

import com.example.base.entities.BasicDto
import com.example.base.entities.BasicEntity
import com.example.domains.Role
import com.example.domains.User
import groovy.transform.CompileStatic
import groovy.transform.ToString
import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable

@CompileStatic
@ToString
@MappedEntity("core_user_role_link")
@Introspected
@Serdeable
class UserRoleLinkDto extends BasicDto{

    private String username

    private String roleCode

    @Override
    String toString(){

        return "Entity: COUNTRY - id: " + getId() + " lastUpdatedDate: " + getLastUpdatedDate() + " insertDate: " + getInsertDate()
    }

    String getUsername() {
        return username
    }

    void setUsername(String username) {
        this.username = username
    }

    String getRoleCode() {
        return roleCode
    }

    void setRoleCode(String roleCode) {
        this.roleCode = roleCode
    }
}