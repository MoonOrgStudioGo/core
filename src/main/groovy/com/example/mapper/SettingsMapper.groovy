package com.example.mapper

import com.example.domains.Settings
import com.example.dto.SettingsDto

class SettingsMapper {

    static Settings dtoToEntity(SettingsDto settingsDto){
        new Settings(
                code: settingsDto.code,
                key: settingsDto.key,
                value: settingsDto.value,
                description: settingsDto.description
        )
    }

    static SettingsDto entityToDto(Settings settings){
        new SettingsDto(
                id: settings.id,
                code: settings.code,
                insertDate: settings.insertDate,
                enabled: settings.enabled,
                lastUpdatedDate: settings.lastUpdatedDate,
                version: settings.version,
                status: settings.status,
                key: settings.key,
                value: settings.value,
                description: settings.description
        )
    }
}
