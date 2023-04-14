package com.java.example.records.user;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.Nullable;

@Introspected
public record UserFindByRecord (Long id, String username, String email, Boolean resetPassword, String code, Long status,
                                String statusDescription, String insertDateString, String lastUpdatedDateString, Long languageId,
                                String languageCode, Long countryId, String countryCode, @Nullable Long companyId, @Nullable String companyCode){


}
