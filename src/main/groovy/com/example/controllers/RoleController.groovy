package com.example.controllers

import com.example.base.exceptions.EntityNotFoundException
import com.example.base.httpresponses.ActionCompletedResponse
import com.example.domains.Role
import com.example.dto.RoleDto
import com.example.mapper.RoleMapper
import com.example.repositories.RoleRepository
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