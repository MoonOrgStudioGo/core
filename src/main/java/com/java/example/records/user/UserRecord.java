package com.java.example.records.user;

import io.micronaut.core.annotation.Introspected;

@Introspected
public record UserRecord(Long userId, Long languageId, String languageCode){

}