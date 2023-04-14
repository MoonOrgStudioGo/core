package core.us.dto

import com.us.base.library.entities.abstracts.classes.BasicDto
import groovy.transform.CompileStatic
import groovy.transform.ToString
import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.MappedEntity

@CompileStatic
@ToString
@MappedEntity("core_user_role_link")
@Introspected
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