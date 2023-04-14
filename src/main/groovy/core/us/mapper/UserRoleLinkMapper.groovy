package core.us.mapper

import core.us.domains.Role
import core.us.domains.User
import core.us.domains.UserRoleLink
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
