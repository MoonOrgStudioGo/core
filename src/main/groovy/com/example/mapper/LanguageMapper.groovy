package com.example.mapper

import com.example.domains.Language
import com.example.dto.LanguageDto

class LanguageMapper {

    static Language dtoToEntity(LanguageDto languageDto){
        new Language(
                code: languageDto.code,
                description: languageDto.description
        )
    }

    static LanguageDto entityToDto(Language language){
        new LanguageDto(
                id: language.id,
                code: language.code,
                insertDate: language.insertDate,
                enabled: language.enabled,
                lastUpdatedDate: language.lastUpdatedDate,
                version: language.version,
                status: language.status,
                description: language.description
        )
    }
}
