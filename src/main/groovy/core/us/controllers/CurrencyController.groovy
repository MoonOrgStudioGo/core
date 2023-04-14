package core.us.controllers

import com.us.base.library.entities.beans.exceptions.exceptions.EntityNotFoundException
import com.us.base.library.entities.beans.httpresponses.ActionCompletedResponse
import core.us.dto.CurrencyDto
import core.us.mapper.CurrencyMapper
import core.us.repositories.CurrencyRepository
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
    CurrencyRepository currencyRepository;

    @Get("/{code}")
    Mono<MutableHttpResponse<ActionCompletedResponse<CurrencyDto>>> findByCode(String code) {
        return currencyRepository.existsByCode(code).flatMap { result ->
            if (!result) {
                throw new EntityNotFoundException("com.example.domains.Currency not found for code: ${code}")
            }
            currencyRepository.findByCode(code)
                                .flatMap { currency ->
                                    Mono.just(HttpResponse.ok(new ActionCompletedResponse<CurrencyDto>(CurrencyMapper.entityToDto(currency))))
            }.doOnError { it -> it.message }.log()
        }
    }

    @Post("/")
    Mono<MutableHttpResponse<ActionCompletedResponse<CurrencyDto>>> save(@Valid CurrencyDto currency) {

        return currencyRepository.save(CurrencyMapper.dtoToEntity(currency)).flatMap{Mono.just(HttpResponse.created(new ActionCompletedResponse<CurrencyDto>(it.id)))}
    }
}