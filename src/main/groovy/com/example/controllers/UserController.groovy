package com.example.controllers

import com.example.base.httpresponses.ActionCompletedResponse
import com.example.domains.User
import com.example.base.exceptions.EntityNotFoundException
import com.example.base.exceptions.NoResetPasswordRequestedException
import com.example.dtos.UserDto
import com.example.repositories.UserRepository
import groovy.transform.CompileStatic
import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put
import jakarta.inject.Inject
import reactor.core.publisher.Mono

import javax.validation.Valid
import javax.validation.constraints.NotNull

@CompileStatic
@Controller("/user")
class UserController {

    @Inject
    UserRepository userRepository;

    @Get("/{id}")
    Mono<MutableHttpResponse<ActionCompletedResponse<User>>> findById(Long id) {
        return userRepository.existsById(id).flatMap{result ->
                if (!result){
                    throw new EntityNotFoundException("User not found for id: ${id}")
                }
            userRepository.findById(id).flatMap{user-> user.hidPassword(); Mono.just(HttpResponse.ok(new ActionCompletedResponse<User>(user)))}
        }.doOnError {it -> it.message}.log()
    }

    @Post("/")
    Mono<MutableHttpResponse<ActionCompletedResponse<UserDto>>> save(@Valid UserDto user) {

        return userRepository.save(user).flatMap{it-> it.hidPassword(); Mono.just(HttpResponse.created(new ActionCompletedResponse<UserDto>(user)))}
    }

    @Put("/{id}")
    Mono<MutableHttpResponse<ActionCompletedResponse<User>>> updatePassword(@NotNull Long id, @NotNull String password) {

        return userRepository.existsById(id).flatMap{it ->
            if (!it){
                throw new EntityNotFoundException("User not found for id: ${id}")
            }
            userRepository.findById(id).flatMap {
                if (!it.resetPassword){
                    throw new NoResetPasswordRequestedException("Reset password not requested")
                }
                it.password = password
                userRepository.update(it)
            }
        }.map {it-> HttpResponse.ok(new ActionCompletedResponse<User>(it.id))}.doOnError{it -> it.message}.log()
    }

    @Delete("/{id}")
    Mono<MutableHttpResponse<ActionCompletedResponse<User>>> delete(Long id){
        userRepository.existsById(id).flatMap {it ->
            if (!it){
                throw new EntityNotFoundException("Entity not found for id: ${id}")
            }
            userRepository.deleteById(id)
        }.map {it-> HttpResponse.ok(new ActionCompletedResponse<User>())}.doOnError {it-> it.message}.log()
    }

}