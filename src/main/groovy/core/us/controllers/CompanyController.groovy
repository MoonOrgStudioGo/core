package core.us.controllers

import com.us.base.library.entities.beans.exceptions.exceptions.EntityNotFoundException
import com.us.base.library.entities.beans.httpresponses.ActionCompletedResponse
import core.us.dto.CompanyDto
import core.us.mapper.CompanyMapper
import core.us.repositories.CompanyRepository
import core.us.repositories.CountryRepository
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
@Controller("/company")
class CompanyController {

    @Inject
    CompanyRepository companyRepository

    @Inject
    CountryRepository countryRepository

    @Get("/{id}")
    Mono<MutableHttpResponse<ActionCompletedResponse<CompanyDto>>> findById(Long id) {
        return companyRepository.existsById(id).flatMap{ result ->
                if (!result){
                    throw new EntityNotFoundException("Company not found for id: ${id}")
                }
            companyRepository.findById(id).flatMap{ user-> Mono.just(HttpResponse.ok(new ActionCompletedResponse<CompanyDto>(CompanyMapper.entityToDto(user))))}
        }.doOnError {it -> it.message}.log()
    }

    @Post("/")
    Mono<MutableHttpResponse<ActionCompletedResponse<CompanyDto>>> save(CompanyDto companyDto) {

        return Mono.from(
            countryRepository.existsByCode(companyDto.countryCode).flatMap { resultCountry ->
                if (!resultCountry) {
                    throw new EntityNotFoundException("Country not found for code: ${companyDto.countryCode}")
                }
                countryRepository.findByCode(companyDto.countryCode)
            })
                .flatMap{country -> companyRepository.save(CompanyMapper.dtoToEntity(companyDto, country))}
                        .flatMap{insertedCompany->
                            Mono.just(HttpResponse.created(new ActionCompletedResponse<CompanyDto>(insertedCompany.id)))}.log()
    }

    @Delete("/{id}")
    Mono<MutableHttpResponse<ActionCompletedResponse<CompanyDto>>> delete(Long id){
        companyRepository.existsById(id).flatMap { it ->
            if (!it){
                throw new EntityNotFoundException("Entity not found for id: ${id}")
            }
            companyRepository.deleteById(id)
        }.map {it-> HttpResponse.ok(new ActionCompletedResponse<CompanyDto>(it))}.doOnError {it-> it.message}.log()
    }

}