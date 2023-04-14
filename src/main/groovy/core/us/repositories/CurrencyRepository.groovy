package core.us.repositories


import core.us.domains.Currency
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.r2dbc.annotation.R2dbcRepository
import io.micronaut.data.repository.reactive.ReactorPageableRepository
import reactor.core.publisher.Mono

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@R2dbcRepository(dialect = Dialect.POSTGRES)
interface CurrencyRepository extends ReactorPageableRepository<Currency, Long> {

    Mono<Currency> findByCode(@NotNull @NotBlank String code)

    Mono<Boolean> existsByCode(@NotNull @NotBlank String code)
}