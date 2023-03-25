package com.example.controllers

import com.example.base.httpresponses.ActionCompletedResponse
import com.example.domains.Settings
import com.example.base.exceptions.EntityNotFoundException
import com.example.domains.User
import com.example.repositories.SettingsRepository
import groovy.transform.CompileStatic
import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.*
import jakarta.inject.Inject
import reactor.core.publisher.Mono

import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@CompileStatic
@Controller("/settings")
class SettingsController {

    @Inject
    SettingsRepository settingsRepository;

    @Get("/{code}")
    Mono<MutableHttpResponse<ActionCompletedResponse<Settings>>> findByCode(@NotNull @NotBlank String code) {
        return settingsRepository.existsByCode(code).flatMap{it ->
                if (!it){
                    throw new EntityNotFoundException("Settings not found for code: ${code}")
                }
            settingsRepository.findByCode(code).flatMap(settings-> Mono.just(HttpResponse.ok(new ActionCompletedResponse<Settings>(settings))))
        }.doOnError { it -> it.message}.log()
    }

    @Post("/")
    Mono<MutableHttpResponse<ActionCompletedResponse<Settings>>> save(@Valid Settings settings) {

        return settingsRepository.save(settings).flatMap{it-> Mono.just(HttpResponse.created(new ActionCompletedResponse<Settings>(it)))}
    }
}