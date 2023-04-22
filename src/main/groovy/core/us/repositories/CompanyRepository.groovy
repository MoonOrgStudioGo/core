package core.us.repositories

import com.us.base.library.entities.core.domains.Company
import io.micronaut.core.annotation.Nullable
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.r2dbc.annotation.R2dbcRepository
import io.micronaut.data.repository.reactive.ReactorPageableRepository
import reactor.core.publisher.Mono

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@R2dbcRepository(dialect = Dialect.POSTGRES)
interface CompanyRepository extends ReactorPageableRepository<Company, Long> {

    Mono<Company> findByCode(@Nullable String code)

    Mono<Boolean> existsByCode(@Nullable String code)
}