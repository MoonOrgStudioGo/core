package com.example.repositories

import com.example.domains.User
import io.micronaut.core.annotation.NonNull
import io.micronaut.data.annotation.Id
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.r2dbc.annotation.R2dbcRepository
import io.micronaut.data.repository.reactive.ReactorPageableRepository
import reactor.core.publisher.Mono

import javax.transaction.Transactional
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@R2dbcRepository(dialect = Dialect.POSTGRES, dataSource = "default")
interface UserRepository extends ReactorPageableRepository<User, Long> {

    Mono<Integer> updatePasswordById(Long id, String password)

    @Transactional
    Mono<Long> updatePassword(@NonNull @NotNull @Id Long id, @NonNull @NotBlank String password)

}