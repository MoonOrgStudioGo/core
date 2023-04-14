package com.example.controllers

import com.us.base.library.exceptions.EntityNotFoundException
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/test")
class TestController {
    @Get
    def test(){
        throw new EntityNotFoundException()
    }

}
