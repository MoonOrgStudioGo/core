package core.us.controllers

import com.us.base.library.entities.beans.exceptions.exceptions.EntityNotFoundException
import com.us.base.library.entities.beans.httpresponses.ActionCompletedResponse
import core.us.domains.Language
import core.us.dto.LanguageDto
import core.us.mapper.LanguageMapper
import core.us.repositories.LanguageRepository
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
@Controller("/language")
class LanguageController {

    @Inject
    LanguageRepository languageRepository;

    @Get("/{code}")
    Mono<MutableHttpResponse<ActionCompletedResponse<LanguageDto>>> findByCode(String code) {
        return languageRepository.existsByCode(code).flatMap { result ->
            if (!result) {
                throw new EntityNotFoundException("Language not found for code: ${code}")
            }
            languageRepository.findByCode(code)
                                .flatMap { language ->
                                    Mono.just(HttpResponse.ok(new ActionCompletedResponse<LanguageDto>(LanguageMapper.entityToDto((language)))))
            }.doOnError { it -> it.message }.log()
        }
    }

    @Post("/")
    Mono<MutableHttpResponse> save(@Valid Language language) {

        return languageRepository.save(language).flatMap{Mono.just(HttpResponse.created(it))}
    }
}