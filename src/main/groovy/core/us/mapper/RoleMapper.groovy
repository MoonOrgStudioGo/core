package core.us.mapper

import core.us.domains.Role
import core.us.dto.RoleDto

class RoleMapper {

    static Role dtoToEntity(RoleDto roleDto){
        new Role(
                code: roleDto.code,
                description: roleDto.description
        )
    }

    static RoleDto entityToDto(Role role){
        new RoleDto(
                id: role.id,
                code: role.code,
                insertDate: role.insertDate,
                enabled: role.enabled,
                lastUpdatedDate: role.lastUpdatedDate,
                version: role.version,
                status: role.status,
                description: role.description
        )
    }
}