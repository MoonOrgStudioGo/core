package com.example.base.handlers

import com.example.base.exceptions.BaseException
import io.micronaut.context.annotation.Requires
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Produces
import io.micronaut.http.server.exceptions.ExceptionHandler
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Produces
@Singleton
@Requires(classes = [BaseException.class, ExceptionHandler.class])
class GlobalExceptionHandler<T extends BaseException> implements ExceptionHandler<T, HttpResponse> {
    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Override
    HttpResponse handle(HttpRequest request, T exception) {
        LOG.error(exception.getLocalizedMessage());
        LOG.error(exception.getCause().getMessage());
        Arrays.stream(exception.getStackTrace()).forEach(item ->  LOG.error(item.toString()));
        return HttpResponse.serverError(exception.getLocalizedMessage());
    }
}
