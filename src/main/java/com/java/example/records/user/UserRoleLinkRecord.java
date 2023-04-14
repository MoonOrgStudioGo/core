package com.java.example.records.user;

import io.micronaut.core.annotation.Introspected;

@Introspected
public record UserRoleLinkRecord(Long id, Long userId, Long roleId, String username, String roleCode){
}
