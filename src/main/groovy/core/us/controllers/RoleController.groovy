package core.us.controllers

import com.us.base.library.entities.beans.exceptions.exceptions.EntityNotFoundException
import com.us.base.library.entities.beans.httpresponses.ActionCompletedResponse
import core.us.domains.Role
import core.us.dto.RoleDto
import core.us.mapper.RoleMapper
import core.us.repositories.RoleRepository
import groovy.transform.CompileStatic
import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import jakarta.inject.Inject
import reactor.core.publisher.Mono

import javax.validation.Valid

@CompileStatic
@Controller("/role")
class RoleController {

    @Inject
    RoleRepository roleRepository;

    @Get("/{code}")
    Mono<MutableHttpResponse<ActionCompletedResponse<RoleDto>>> findByCode(String code) {
        return roleRepository.existsByCode(code).flatMap { result ->
            if (!result) {
                throw new EntityNotFoundException("Role not found for code: ${code}")
            }
            roleRepository.findByCode(code)
                                .flatMap { role ->
                                    Mono.just(HttpResponse.ok(new ActionCompletedResponse<RoleDto>(RoleMapper.entityToDto((role)))))
            }.doOnError { it -> it.message }.log()
        }
    }

    @Post("/")
    Mono<MutableHttpResponse> save(@Valid Role role) {

        return roleRepository.save(role).flatMap{Mono.just(HttpResponse.created(it))}
    }
}