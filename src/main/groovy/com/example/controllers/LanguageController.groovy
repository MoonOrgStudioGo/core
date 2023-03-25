package com.example.controllers

import com.example.base.exceptions.EntityNotFoundException
import com.example.domains.Language
import com.example.repositories.LanguageRepository
import groovy.transform.CompileStatic
import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.*
import jakarta.inject.Inject
import reactor.core.publisher.Mono

import javax.validation.Valid

@CompileStatic
@Controller("/language")
class LanguageController {

    @Inject
    LanguageRepository languageRepository;

    @Get("/{code}")
    Mono<MutableHttpResponse<Language>> findByCode(String code) {
        return languageRepository.existsByCode(code).flatMap { result ->
            if (!result) {
                throw new EntityNotFoundException("Language not found for code: ${code}")
            }
            languageRepository.findByCode(code).flatMap { language -> Mono.just(HttpResponse.ok(language))
            }.doOnError { it -> it.message }.log()
        }
    }

    @Post("/")
    Mono<MutableHttpResponse> save(@Valid Language language) {

        return languageRepository.save(language).flatMap{Mono.just(HttpResponse.created(it))}
    }
}