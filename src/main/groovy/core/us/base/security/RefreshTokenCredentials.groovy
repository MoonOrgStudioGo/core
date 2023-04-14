package core.us.base.security

import groovy.transform.CompileStatic
import groovy.transform.ToString
import io.micronaut.core.annotation.Introspected

@CompileStatic
@ToString
@Introspected
class RefreshTokenCredentials {

    String username

    String refreshToken
}
