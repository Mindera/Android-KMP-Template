package com.mindera.di

import com.mindera.client.Darwin
import com.mindera.client.configureHttpClient
import io.ktor.client.plugins.logging.LogLevel

class IOSClientEngineFactory: ClientEngineFactory {
    override fun configureHttpClient() = configureHttpClient(Darwin, 10_000L, LogLevel.ALL)
}

actual fun getConfigureHttpClient(): ClientEngineFactory = IOSClientEngineFactory()
