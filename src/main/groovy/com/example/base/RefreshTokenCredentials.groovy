package com.example.base

import groovy.transform.CompileStatic
import groovy.transform.ToString
import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable

@CompileStatic
@ToString
@Serdeable
@Introspected
class RefreshTokenCredentials {

    String username

    String refreshToken
}
