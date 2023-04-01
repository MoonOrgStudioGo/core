package com.example.controllers

import com.example.base.exceptions.EntityNotFoundException
import com.example.base.httpresponses.ActionCompletedResponse
import com.example.dto.SettingsDto
import com.example.mapper.SettingsMapper
import com.example.repositories.SettingsRepository
import groovy.transform.CompileStatic
import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
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
    Mono<MutableHttpResponse<ActionCompletedResponse<SettingsDto>>> findByCode(@NotNull @NotBlank String code) {
        return settingsRepository.existsByCode(code).flatMap{it ->
                if (!it){
                    throw new EntityNotFoundException("Settings not found for code: ${code}")
                }
            settingsRepository.findByCode(code)
                                .flatMap(settings->
                                        Mono.just(HttpResponse.ok(new ActionCompletedResponse<SettingsDto>(SettingsMapper.entityToDto(settings)))))
        }.doOnError { it -> it.message}.log()
    }

    @Post("/")
    Mono<MutableHttpResponse<ActionCompletedResponse<SettingsDto>>> save(@Valid SettingsDto settingsDto) {

        return settingsRepository.save(SettingsMapper.dtoToEntity(settingsDto)).flatMap{it-> Mono.just(HttpResponse.created(new ActionCompletedResponse<SettingsDto>(it.id)))}
    }
}