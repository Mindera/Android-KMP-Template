package com.mindera.client

import com.mindera.client.interceptor.httpResponseValidator
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

@OptIn(ExperimentalSerializationApi::class)
fun <T : HttpClientEngineConfig> configureHttpClient(
    engineFactory: HttpClientEngineFactory<T>,
    timeoutMillis: Long,
    logLevel: LogLevel,
) = HttpClient(engineFactory = engineFactory) {
    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                // LOG.v("Api", message)
            }
        }
        level = logLevel
    }
    install(HttpTimeout) {
        requestTimeoutMillis = timeoutMillis
        connectTimeoutMillis = timeoutMillis
        socketTimeoutMillis = timeoutMillis
    }
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
            explicitNulls = true
        })
    }

    httpResponseValidator()
}
