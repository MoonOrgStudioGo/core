package com.example.mapper

import com.example.domains.Role
import com.example.domains.User
import com.example.domains.UserRoleLink
import com.example.dto.UserRoleLinkDto

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
