package com.example.mapper

import com.example.domains.Company
import com.example.domains.Country
import com.example.dto.CompanyDto

class CompanyMapper {

    static Company dtoToEntity(CompanyDto settingsDto, Country country){
        new Company(
                code: settingsDto.code,
                description: settingsDto.description,
                country: country,
                vatNumber: settingsDto.vatNumber,
                businessName: settingsDto.businessName,
                address: settingsDto.address,
                email: settingsDto.email,
                phoneNumber: settingsDto.phoneNumber
        )
    }

    static CompanyDto entityToDto(Company company){
        new CompanyDto(
                id: company.id,
                code: company.code,
                insertDate: company.insertDate,
                enabled: company.enabled,
                lastUpdatedDate: company.lastUpdatedDate,
                version: company.version,
                status: company.status,
                description: company.description,
                countryCode: company.country.code,
                vatNumber: company.vatNumber,
                businessName: company.businessName,
                address: company.address,
                email: company.email,
                phoneNumber: company.phoneNumber,
        )
    }
}
