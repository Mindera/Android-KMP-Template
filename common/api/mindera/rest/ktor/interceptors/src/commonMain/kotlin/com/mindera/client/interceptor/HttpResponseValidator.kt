package com.mindera.client.interceptor

import com.mindera.kmpexample.domain.exceptions.Error.Generic
import com.mindera.kmpexample.domain.exceptions.Error.Network
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.CallRequestExceptionHandler
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.request.HttpRequest
import io.ktor.http.isSuccess
import io.ktor.utils.io.errors.IOException

fun HttpClientConfig<*>.httpResponseValidator() = HttpResponseValidator {
    validateResponse { response ->
        if (!response.status.isSuccess()) {
            throw Generic(
                message = "${response.status}:${response.status.description}",
                cause = Throwable("The API response contains errors")
            )
        }
    }

    handleResponseExceptionWithRequest(object : CallRequestExceptionHandler {
        override suspend fun invoke(cause: Throwable, request: HttpRequest) {
            when (cause) {
                is IOException -> throw Network(
                    message = cause.message,
                    cause = cause.cause
                )

                else -> throw Generic(
                    message = cause.message,
                    cause = cause.cause
                )
            }
        }
    })
}
