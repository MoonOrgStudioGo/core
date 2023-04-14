package core.us.mapper

import com.us.base.library.entities.core.domains.Role
import com.us.base.library.entities.core.domains.User
import com.us.base.library.entities.core.domains.UserRoleLink
import core.us.dto.UserRoleLinkDto

class UserRoleLinkMapper {

    static UserRoleLink dtoToEntity(Role role, User user){
        new UserRoleLink(
                user: user,
                role: role
        )
    }

    static UserRoleLinkDto entityToDto(UserRoleLink userRoleLink){
        new UserRoleLinkDto(
            id: userRoleLink.id
        )
    }
}
