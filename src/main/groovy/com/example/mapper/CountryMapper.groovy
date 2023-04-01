package com.example.mapper

import com.example.domains.Country
import com.example.domains.Currency
import com.example.domains.Language
import com.example.dto.CountryDto

class CountryMapper {

    static Country dtoToEntity(CountryDto settingsDto, Language language, Currency currency){
        new Country(
                code: settingsDto.code,
                description: settingsDto.description,
                language: language,
                currency: currency
        )
    }

    static CountryDto entityToDto(Country country){
        new CountryDto(
                id: country.id,
                code: country.code,
                languageCode: country.language.code,
                currencyCode: country.currency.code,
                insertDate: country.insertDate,
                enabled: country.enabled,
                lastUpdatedDate: country.lastUpdatedDate,
                version: country.version,
                status: country.status,
                description: country.description
        )
    }
}
