package core.us.mapper

import com.us.base.library.entities.core.domains.Language
import core.us.dto.LanguageDto

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
