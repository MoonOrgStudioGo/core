package com.java.example;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Introspected
@Serdeable
public record UserRecord(Long userId, Long languageId, String languageCode){

}