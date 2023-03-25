package com.example.mappers

import com.example.domains.Language
import com.example.domains.User
import com.example.dtos.UserDto
import org.mapstruct.*
import org.mapstruct.factory.Mappers

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class)

    @Mapping(target = "user.id", ignore = true)
    @Mapping(target = "user.insertDate", ignore = true )
    @Mapping(target = "user.lastUpdatedDate", ignore = true)
    @Mapping(target = "user.password", ignore = true)
    @Mapping(target = "user.language", source = "language")
    @Mapping(target = "user.email", source = "userDto.email")
    @Mapping(target = "user.status", source = "userDto.status")
    @Mapping(target = "user.code", source = "userDto.code")
    @Mapping(target = "user.enabled", source = "userDto.enabled")
    @Mapping(target = "user.country", source = "country")
    User dtoToEntity(@MappingTarget User user, UserDto userDto, Language language)//, Company company, Country country)

    @Mapping(target = "userDto.password", ignore = true)
    UserDto entityToDto(@MappingTarget UserDto userDto, User user)
}
