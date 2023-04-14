package core.us.domains


import com.us.base.library.entities.abstracts.classes.BasicLinkEntity
import groovy.transform.CompileStatic
import groovy.transform.ToString
import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.MappedEntity

@CompileStatic
@ToString
@MappedEntity("core_user_role_link")
@Introspected
class UserRoleLink extends BasicLinkEntity{

    static final String TABLE_NAME = "core_user_role_link"
    static final String ENTITY_NAME = "User Role link"

    private User user

    private Role role

    User getUser() {
        return user
    }

    void setUser(User user) {
        this.user = user
    }

    Role getRole() {
        return role
    }

    void setRole(Role role) {
        this.role = role
    }
}