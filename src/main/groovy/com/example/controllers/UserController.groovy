package com.example.controllers

import com.example.base.RefreshTokenCredentials
import com.java.example.records.user.UserFindByRecord
import com.us.base.library.entities.UsernamePasswordCredentials
import com.us.base.library.entities.implementations.CoreSessionData
import com.us.base.library.exceptions.EntityNotFoundException
import com.us.base.library.exceptions.NoResetPasswordRequestedException
import com.us.base.library.httpresponses.ActionCompletedResponse
import com.us.base.library.utility.Utility
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
import com.java.example.records.user.UserRecord
import com.example.repositories.CompanyRepository
import com.example.repositories.CountryRepository
import com.example.repositories.LanguageRepository
import com.example.repositories.UserRepository
import groovy.transform.CompileStatic
import io.micronaut.core.annotation.NonNull
import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import jakarta.inject.Inject
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

import javax.validation.Valid
import javax.validation.constraints.NotNull
import java.sql.Timestamp

@CompileStatic
@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("api/user")
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
    Mono<MutableHttpResponse<ActionCompletedResponse<UserFindByRecord>>> findById(@RequestAttribute("sessionData") CoreSessionData sessionData, Long id) {
        return userRepository.existsById(id).flatMap{result ->
                if (!result){
                    throw new EntityNotFoundException("User not found for id: ${id}")
                }
            File f = new File("/Users/alessandro/aleProject/core/a.txt")
            userRepository.findByIdEquals(id).map{user-> HttpResponse.ok(new ActionCompletedResponse<UserFindByRecord>(user))}
        }.doOnError {it -> it.message}.log()
    }

    @Get("/list/")
    Flux<UserRecord> list() {
        return userRepository.list()
    }

    @Post("/")
    Mono<MutableHttpResponse<ActionCompletedResponse<UserDto>>> save(@NonNull @Valid UserDto userDto) {

        return Mono.zip(

                //Check language
                languageRepository.existsByCode(userDto.languageCode).flatMap { resultLanguage ->
                    if (!resultLanguage) {
                        throw new EntityNotFoundException("Language not found for code: ${userDto.languageCode}")
                    }
                    languageRepository.findByCode(userDto.languageCode)} as Mono<Language>,

                //Check country
                countryRepository.existsByCode(userDto.countryCode).flatMap { resultCountry ->
                    if (!resultCountry) {
                        throw new EntityNotFoundException("Country not found for code: ${userDto.countryCode}")
                    }
                    countryRepository.findByCode(userDto.countryCode)} as Mono<Country>,

                //Check Company
                companyRepository.existsByCode(userDto.companyCode).flatMap { resultCompany ->
                if (!resultCompany) {
                    throw new EntityNotFoundException("Company not found for code: ${userDto.companyCode}")
                }
                companyRepository.findByCode(userDto.companyCode)} as Mono<Company>
            )
                .flatMap{tuple -> userRepository.save(UserMapper.dtoToEntity(userDto, tuple.getT1(), tuple.getT2(), tuple.getT3()))
                        .map{insertedUser->
                           HttpResponse.created(new ActionCompletedResponse<UserDto>(insertedUser.id))}}.doOnError {it -> it.message}.log()
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

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Put("/updateRefreshToken")
    Mono<MutableHttpResponse<ActionCompletedResponse<UserDto>>> updateRefreshToken(@Body RefreshTokenCredentials refreshTokenCredentials) {

        return userRepository.existsByUsername(refreshTokenCredentials.username).flatMap{it ->
            if (!it){
                throw new EntityNotFoundException("User not found for username: ${refreshTokenCredentials.username}")
            }
            userRepository.findByUsername(refreshTokenCredentials.username).flatMap {
                it.refreshToken = refreshTokenCredentials.refreshToken
                it.insertRefreshToken = new Timestamp(new Date().getTime())
                userRepository.update(it)
            }
        }.map {it-> HttpResponse.ok(new ActionCompletedResponse<UserDto>(it.id))}.doOnError{it -> it.message}.log()
    }

    @Put("/checkCredentials")
    @Secured(SecurityRule.IS_ANONYMOUS)
    Mono<MutableHttpResponse<ActionCompletedResponse<Map>>> checkCredentials(@Body UsernamePasswordCredentials credentials){

        return userRepository.existsByUsername(credentials.username).flatMap {userExists ->
            if (!userExists){
                throw new EntityNotFoundException("User not found for username: ${credentials.username}")
            }
            userRepository.findByUsername(credentials.username)
        }.flatMap {user ->

            Long companyId = user.company.id != null ? user.company.id : -1
            Long languageId = user.language.id != null ? user.language.id : -1
            Long countryId = user.country.id != null ? user.country.id : -1
            String hashedPw = Utility.hashPassword(credentials.password)

            if (user.password != hashedPw){
                throw new EntityNotFoundException("Wrong password")
            }

            Mono.zip(
                    companyRepository.existsById(companyId)
                        .flatMap {companyExists ->
                            if (companyExists){
                                companyRepository.findById(companyId)
                                        .flatMap {it ->
                                            Mono.just(it.code)
                                        }
                            } else {
                                Mono.just("")
                            }
                        },
                    languageRepository.existsById(languageId)
                        .flatMap {languageExists ->
                            if (languageExists){
                                languageRepository.findById(languageId)
                                        .flatMap {it ->
                                            Mono.just(it.code)
                                        }
                            }
                        },
                    countryRepository.existsById(countryId)
                        .flatMap {countryExists ->
                            if (countryExists){
                                countryRepository.findById(countryId)
                                    .flatMap {it ->
                                        Mono.just(it.code)
                                    }
                            }
                        },
                    userRoleLinkRepository.findAllCodeRoleByUserId(user.id).collectList()
            ).flatMap {tuple ->
                String companyCode  = tuple.getT1()
                String languageCode = tuple.getT2()
                String countryCode = tuple.getT3()
                List rolesList = tuple.getT4()

                Mono.just(HttpResponse.ok(new ActionCompletedResponse<Map>([username: user.username, attributes: [companyCode: companyCode, languageCode: languageCode, countryCode: countryCode], roles: rolesList])))
            }
        }



       /* Mono.zip(languageRepository.existsByCode(userDto.languageCode).flatMap { resultLanguage ->
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
                Mono.just(HttpResponse.created(new ActionCompletedResponse<UserDto>(insertedUser.id)))}}.log()*/
}

    @Get("/checkRefreshToken")
    Mono<MutableHttpResponse<ActionCompletedResponse<UserDto>>> checkRefreshToken(@QueryValue String refreshToken){
        userRepository.existsByRefreshToken(refreshToken).flatMap {it ->
            if (!it){
                throw new EntityNotFoundException("User not found for refresh token: ${refreshToken}")
            }
            userRepository.findByRefreshToken(refreshToken)
        }.map {it ->
            HttpResponse.ok(new ActionCompletedResponse<UserDto>(UserMapper.entityToDto(it)))
        }.doOnError(it -> it.message)
    }

    @Get("/getRefreshToken")
    Mono<MutableHttpResponse<ActionCompletedResponse<UserDto>>> getRefreshToken(@QueryValue String username, @CookieValue("JWT") jwtRefreshToken){
        userRepository.existsByUsername(username).flatMap {it ->
            if (!it){
                throw new EntityNotFoundException("User not found for username: ${username}")
            }
            userRepository.findByUsername(username)
        }.map {it ->
            HttpResponse.ok(new ActionCompletedResponse<UserDto>(UserMapper.entityToDto(it)))
        }.doOnError(it -> it.message)
    }

}