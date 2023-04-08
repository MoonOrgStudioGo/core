package com.example.base.security

import io.micronaut.core.annotation.Nullable
import io.micronaut.http.HttpRequest
import io.micronaut.security.authentication.Authentication
import io.micronaut.security.rules.SecurityRule
import io.micronaut.security.rules.SecurityRuleResult
import io.micronaut.web.router.RouteMatch
import org.reactivestreams.Publisher
import reactor.core.publisher.Mono

@Singleton
class PermissionSecurityRule implements SecurityRule {

    @Override
    Publisher<SecurityRuleResult> check(HttpRequest<?> request, @Nullable RouteMatch<?> routeMatch, @Nullable Authentication authentication) {
        return Mono.just(SecurityRuleResult.ALLOWED)
    }
}