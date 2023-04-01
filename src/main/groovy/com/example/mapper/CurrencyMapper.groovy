package com.example.mapper

import com.example.domains.Currency
import com.example.dto.CurrencyDto

class CurrencyMapper {

    static Currency dtoToEntity(CurrencyDto settingsDto){
        new Currency(
                code: settingsDto.code,
                description: settingsDto.description
        )
    }

    static CurrencyDto entityToDto(Currency settings){
        new CurrencyDto(
                id: settings.id,
                code: settings.code,
                insertDate: settings.insertDate,
                enabled: settings.enabled,
                lastUpdatedDate: settings.lastUpdatedDate,
                version: settings.version,
                status: settings.status,
                description: settings.description
        )
    }
}
