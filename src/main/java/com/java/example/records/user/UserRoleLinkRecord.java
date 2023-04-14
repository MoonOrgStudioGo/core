package com.java.example.records.user;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Introspected
@Serdeable
public record UserRoleLinkRecord(Long id, Long userId, Long roleId, String username, String roleCode){
}
