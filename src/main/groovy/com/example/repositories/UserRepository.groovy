package com.example.repositories


import com.example.domains.User
import com.java.example.records.user.UserFindByRecord
import io.micronaut.core.annotation.NonNull
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.Query
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.r2dbc.annotation.R2dbcRepository
import io.micronaut.data.repository.reactive.ReactorPageableRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import com.java.example.records.user.UserRecord

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

    @Query("""SELECT u.id as id, u.username as username, u.email as email, u.telephone_number, to_char(u.insert_date,'dd/mm/yyyy hh-MM') as insert_date_string,
                     to_char(u.last_updated_date,'dd/mm/yyyy hh-MM') as last_updated_date_string, u.code,fn_get_status_description('ST_USER', u.status, l.code) as status_description, 
                     u.status as status, u.reset_password, 
                    l.id  as language_id, l.code as language_code, 
                    comp.id as company_id, comp.code as company_code,
                    coun.id as country_id, coun.code as country_code
              FROM core_user u 
              INNER JOIN core_language l ON u.language_id = l.id
              LEFT JOIN core_company comp ON u.company_id = comp.id
              INNER JOIN core_language coun ON u.country_id = coun.id
              where u.id = :id
             """)
    Mono<UserFindByRecord> findByIdEquals(Long id);

}