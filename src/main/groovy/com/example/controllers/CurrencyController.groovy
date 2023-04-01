package com.example.controllers

import com.example.base.exceptions.EntityNotFoundException
import com.example.base.httpresponses.ActionCompletedResponse
import com.example.dto.CurrencyDto
import com.example.mapper.CurrencyMapper
import com.example.repositories.CurrencyRepository
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
@Controller("/currency")
class CurrencyController {

    @Inject
    CurrencyRepository countryRepository;

    @Get("/{code}")
    Mono<MutableHttpResponse<ActionCompletedResponse<CurrencyDto>>> findByCode(String code) {
        return countryRepository.existsByCode(code).flatMap { result ->
            if (!result) {
                throw new EntityNotFoundException("com.example.domains.Currency not found for code: ${code}")
            }
            countryRepository.findByCode(code)
                                .flatMap { country ->
                                    Mono.just(HttpResponse.ok(new ActionCompletedResponse<CurrencyDto>(CurrencyMapper.entityToDto((country)))))
            }.doOnError { it -> it.message }.log()
        }
    }

    @Post("/")
    Mono<MutableHttpResponse<ActionCompletedResponse<CurrencyDto>>> save(@Valid CurrencyDto currency) {

        return countryRepository.save(CurrencyMapper.dtoToEntity(currency)).flatMap{Mono.just(HttpResponse.created(new ActionCompletedResponse<CurrencyDto>(it.id)))}
    }
}