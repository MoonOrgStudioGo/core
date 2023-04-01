package com.example.repositories

import com.example.domains.UserRoleLink
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.r2dbc.annotation.R2dbcRepository
import io.micronaut.data.repository.reactive.ReactorPageableRepository

@R2dbcRepository(dialect = Dialect.POSTGRES)
interface UserRoleLinkRepository extends ReactorPageableRepository<UserRoleLink, Long> {

}