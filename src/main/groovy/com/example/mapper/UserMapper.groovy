package com.example.mapper

import com.example.domains.Company
import com.example.domains.Country
import com.example.domains.Language
import com.example.domains.User
import com.example.dto.UserDto


import static com.example.Constants.*
import static com.example.base.utility.Utility.sqlTimestampToString

class UserMapper {

    static User dtoToEntity(UserDto userDto, Language language, Country country, Company company){
        new User(
                username: userDto.username,
                password: userDto.password,
                email: userDto.email,
                telephoneNumber:  userDto.telephoneNumber,
                language:  language,
                country: country,
                code: userDto.code,
                company: company
        )
    }

    static UserDto entityToDto(User user){

        new UserDto(
                id: user.id,
                code: user.code,
                username: user.username,
                email: user.email,
                telephoneNumber: user.telephoneNumber,
                refreshToken: user.refreshToken,
                insertDate: sqlTimestampToString(user.insertDate, defaultDateFormat),
                insertRefreshToken: sqlTimestampToString(user.insertRefreshToken, defaultDateFormat),
                resetPassword: user.resetPassword,
                languageCode: user.language.code,
                countryCode: user.country.code,
                enabled: user.enabled,
                lastUpdatedDate: sqlTimestampToString(user.lastUpdatedDate, defaultDateFormat),
                version: user.version,
                status: user.status,
                companyCode: user.company.code
        )
    }
}
