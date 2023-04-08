package com.example.controllers

import com.us.base.library.exceptions.EntityNotFoundException
import com.us.base.library.httpresponses.ActionCompletedResponse
import com.example.domains.Currency
import com.example.domains.Language
import com.example.dto.CountryDto
import com.example.mapper.CountryMapper
import com.example.repositories.CountryRepository
import com.example.repositories.CurrencyRepository
import com.example.repositories.LanguageRepository
import groovy.transform.CompileStatic
import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import jakarta.inject.Inject
import reactor.core.publisher.Mono

@CompileStatic
@Controller("/country")
class CountryController {

    @Inject
    CountryRepository countryRepository

    @Inject
    LanguageRepository languageRepository

    @Inject
    CurrencyRepository currencyRepository

    @Get("/{id}")
    Mono<MutableHttpResponse<ActionCompletedResponse<CountryDto>>> findById(Long id) {
        return countryRepository.existsById(id).flatMap{ result ->
                if (!result){
                    throw new EntityNotFoundException("Country not found for id: ${id}")
                }
            countryRepository.findById(id).flatMap{ country-> Mono.just(HttpResponse.ok(new ActionCompletedResponse<CountryDto>(CountryMapper.entityToDto(country))))}
        }.doOnError {it -> it.message}.log()
    }

    @Post("/")
    Mono<MutableHttpResponse<ActionCompletedResponse<CountryDto>>> save(CountryDto countryDto) {

        return Mono.zip(languageRepository.existsByCode(countryDto.languageCode).flatMap { resultLanguage ->
            if (!resultLanguage) {
                throw new EntityNotFoundException("Language not found for code: ${countryDto.languageCode}")
            }
            languageRepository.findByCode(countryDto.languageCode)} as Mono<Language>,
                currencyRepository.existsByCode(countryDto.currencyCode).flatMap { resultCountry ->
                    if (!resultCountry) {
                        throw new EntityNotFoundException("Currency not found for code: ${countryDto.currencyCode}")
                    }
                    currencyRepository.findByCode(countryDto.currencyCode)} as Mono<Currency>
        )
                .flatMap{tuple -> countryRepository.save(CountryMapper.dtoToEntity(countryDto, tuple.getT1(), tuple.getT2()))
                        .flatMap{insertedUser->
                            Mono.just(HttpResponse.created(new ActionCompletedResponse<CountryDto>(insertedUser.id)))}}.log()
    }

    @Delete("/{id}")
    Mono<MutableHttpResponse<ActionCompletedResponse<CountryDto>>> delete(Long id){
        countryRepository.existsById(id).flatMap { it ->
            if (!it){
                throw new EntityNotFoundException("Entity not found for id: ${id}")
            }
            countryRepository.deleteById(id)
        }.map {it-> HttpResponse.ok(new ActionCompletedResponse<CountryDto>(it))}.doOnError {it-> it.message}.log()
    }

}