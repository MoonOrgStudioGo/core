package com.example.repositories

import com.example.domains.Language
import com.example.domains.User
import io.micronaut.core.annotation.NonNull
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.Join
import io.micronaut.data.annotation.Query
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.r2dbc.annotation.R2dbcRepository
import io.micronaut.data.repository.reactive.ReactorPageableRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import com.java.example.UserRecord

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@R2dbcRepository(dialect = Dialect.POSTGRES)
interface UserRepository extends ReactorPageableRepository<User, Long> {

    Mono<User> findByUsername(@NotNull @NotBlank String username)

    Mono<Boolean> existsByUsername(@NotNull @NotBlank String username)

    Mono<User> findByRefreshToken(@NotNull @NotBlank String refreshToken)

    Mono<Boolean> existsByRefreshToken(@NotNull @NotBlank String refreshToken)

    Mono<Integer> updatePasswordById(Long id, String password)

    Mono<Long> updatePassword(@NonNull @NotNull @Id Long id, @NonNull @NotBlank String password)


    @Query("""SELECT u.id as user_id, l.id  as language_id, l.code as language_code
              FROM core_user u 
              INNER JOIN core_language l ON u.language_id = l.id
               limit 5""")
    Flux<UserRecord> list();

}