package com.example.base.handlers

import com.example.base.errors.ErrorMessage
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Error

@Controller
class ExceptionHandler{

    @Error(global = true)
    HttpResponse<ErrorMessage> exception(HttpRequest request, Throwable e) {
        ErrorMessage error = new ErrorMessage(e.message)

        HttpResponse.<ErrorMessage>serverError()
                .body(error)
    }
}
