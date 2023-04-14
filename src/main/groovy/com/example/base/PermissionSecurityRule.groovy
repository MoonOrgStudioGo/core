package com.example.base

import io.micronaut.core.annotation.Nullable
import io.micronaut.http.HttpRequest
import io.micronaut.security.authentication.Authentication
import io.micronaut.security.rules.AbstractSecurityRule
import io.micronaut.security.rules.IpPatternsRule
import io.micronaut.security.rules.SecuredAnnotationRule
import io.micronaut.security.rules.SecurityRuleResult
import io.micronaut.security.token.RolesFinder
import io.micronaut.web.router.RouteMatch
import jakarta.inject.Singleton
import org.reactivestreams.Publisher
import reactor.core.publisher.Mono

@Singleton
class PermissionSecurityRule extends AbstractSecurityRule {

    public static final Integer ORDER = SecuredAnnotationRule.ORDER - 90;/**
     * @param rolesFinder Roles Parser
     */

    PermissionSecurityRule(RolesFinder rolesFinder) {
        super(rolesFinder)
    }

    @Override
    int getOrder() {
        return ORDER;
    }

    @Override
    Publisher<SecurityRuleResult> check(HttpRequest<?> request, @Nullable RouteMatch<?> routeMatch, @Nullable Authentication authentication) {

        return Mono.just(SecurityRuleResult.ALLOWED)
    }
}