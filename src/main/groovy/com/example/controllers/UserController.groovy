package com.example.controllers

import com.example.base.entities.UsernamePasswordCredentials
import com.example.base.exceptions.CredentialsNotFoundException
import com.example.base.exceptions.EntityNotFoundException
import com.example.base.exceptions.NoResetPasswordRequestedException
import com.example.base.httpresponses.ActionCompletedResponse
import com.example.base.utility.Utility
import com.example.domains.Company
import com.example.domains.Country
import com.example.domains.Language
import com.example.domains.Role
import com.example.domains.User
import com.example.dto.UserDto
import com.example.dto.UserRoleLinkDto
import com.example.mapper.UserMapper
import com.example.mapper.UserRoleLinkMapper
import com.example.repositories.RoleRepository
import com.example.repositories.UserRoleLinkRepository
import com.java.example.UserRecord
import com.example.repositories.CompanyRepository
import com.example.repositories.CountryRepository
import com.example.repositories.LanguageRepository
import com.example.repositories.UserRepository
import groovy.transform.CompileStatic
import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.*
import jakarta.inject.Inject
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

import javax.validation.constraints.NotNull

@CompileStatic
@Controller("/user")
class UserController {

    @Inject
    UserRepository userRepository

    @Inject
    LanguageRepository languageRepository

    @Inject
    CountryRepository countryRepository

    @Inject
    CompanyRepository companyRepository

    @Inject
    RoleRepository roleRepository

    @Inject
    UserRoleLinkRepository userRoleLinkRepository

    @Get("/{id}")
    Mono<MutableHttpResponse<ActionCompletedResponse<UserDto>>> findById(Long id) {
        return userRepository.existsById(id).flatMap{result ->
                if (!result){
                    throw new EntityNotFoundException("User not found for id: ${id}")
                }
            userRepository.findById(id).flatMap{user-> Mono.just(HttpResponse.ok(new ActionCompletedResponse<UserDto>(UserMapper.entityToDto(user))))}
        }.doOnError {it -> it.message}.log()
    }

    @Get("/list/")
    Flux<UserRecord> list() {
        return userRepository.list()
    }

    @Post("/")
    Mono<MutableHttpResponse<ActionCompletedResponse<UserDto>>> save(UserDto userDto) {

        return Mono.zip(languageRepository.existsByCode(userDto.languageCode).flatMap { resultLanguage ->
            if (!resultLanguage) {
                throw new EntityNotFoundException("Language not found for code: ${userDto.languageCode}")
            }
            languageRepository.findByCode(userDto.languageCode)} as Mono<Language>,
                countryRepository.existsByCode(userDto.countryCode).flatMap { resultCountry ->
                    if (!resultCountry) {
                        throw new EntityNotFoundException("Country not found for code: ${userDto.countryCode}")
                    }
                    countryRepository.findByCode(userDto.countryCode)} as Mono<Country>,
                companyRepository.existsByCode(userDto.companyCode).flatMap { resultCountry ->
                if (!resultCountry) {
                    throw new EntityNotFoundException("Company not found for code: ${userDto.companyCode}")
                }
                companyRepository.findByCode(userDto.companyCode)} as Mono<Company>
            )
                .flatMap{tuple -> userRepository.save(UserMapper.dtoToEntity(userDto, tuple.getT1(), tuple.getT2(), tuple.getT3()))
                        .flatMap{insertedUser->
                            Mono.just(HttpResponse.created(new ActionCompletedResponse<UserDto>(insertedUser.id)))}}.log()
    }

    @Put("/{id}")
    Mono<MutableHttpResponse<ActionCompletedResponse<UserDto>>> updatePassword(@NotNull Long id, @NotNull String password) {

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
        }.map {it-> HttpResponse.ok(new ActionCompletedResponse<UserDto>(it.id))}.doOnError{it -> it.message}.log()
    }

    @Delete("/{id}")
    Mono<MutableHttpResponse<ActionCompletedResponse<UserDto>>> delete(Long id){
        userRepository.existsById(id).flatMap {it ->
            if (!it){
                throw new EntityNotFoundException("Entity not found for id: ${id}")
            }
            userRepository.deleteById(id)
        }.map {it-> HttpResponse.ok(new ActionCompletedResponse<UserDto>(it))}.doOnError {it-> it.message}.log()
    }

    @Post("/link/role")
    Mono<MutableHttpResponse<ActionCompletedResponse<UserDto>>> saveRoleLink(UserRoleLinkDto userRoleLinkDto) {

        return Mono.zip(userRepository.existsByUsername(userRoleLinkDto.username).flatMap { resultLanguage ->
            if (!resultLanguage) {
                throw new EntityNotFoundException("User not found for username: ${userRoleLinkDto.username}")
            }
            userRepository.findByUsername(userRoleLinkDto.username)} as Mono<User>,
                roleRepository.existsByCode(userRoleLinkDto.roleCode).flatMap { resultCountry ->
                    if (!resultCountry) {
                        throw new EntityNotFoundException("Role not found for code: ${userRoleLinkDto.roleCode}")
                    }
                    roleRepository.findByCode(userRoleLinkDto.roleCode)} as Mono<Role>
        )
                .flatMap{tuple -> userRoleLinkRepository.save(UserRoleLinkMapper.dtoToEntity(tuple.getT2(), tuple.getT1()))
                        .flatMap{insertedUser->
                            Mono.just(HttpResponse.created(new ActionCompletedResponse<UserDto>(insertedUser.id)))}}.log()
    }

    @Post("/checkCredentials")
    Mono<MutableHttpResponse<ActionCompletedResponse<UserDto>>> checkCredentials(UsernamePasswordCredentials credentials){
        userRepository.existsByUsername(credentials.username).flatMap {it ->
            if (!it){
                throw new EntityNotFoundException("User not found for username: ${credentials.username}")
            }
            userRepository.findByUsername(credentials.username)
        }.flatMap {it ->
            if (Utility.hashPassword(credentials.password) == it.password){
                return Mono.just(HttpResponse.ok(new ActionCompletedResponse<UserDto>()))
            } else {
                throw new CredentialsNotFoundException("Wrong username or password")
            }
        }
    }

}