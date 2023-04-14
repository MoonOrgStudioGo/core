package core.us.repositories

import com.us.base.library.entities.core.domains.UserRoleLink
import io.micronaut.data.annotation.Query
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.r2dbc.annotation.R2dbcRepository
import io.micronaut.data.repository.reactive.ReactorPageableRepository
import reactor.core.publisher.Flux

@R2dbcRepository(dialect = Dialect.POSTGRES)
interface UserRoleLinkRepository extends ReactorPageableRepository<UserRoleLink, Long> {

    @Query("""SELECT rl.code as role_code
                FROM core_user_role_link url
                    JOIN core_user usr on usr.id = url.user_id
                    JOIN core_role rl on rl.id = url.role_id
                where usr.id = :userId
                """)
    Flux<String> findAllCodeRoleByUserId(Long userId)
}