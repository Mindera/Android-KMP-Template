package com.mindera.di

import com.mindera.client.configureHttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.logging.LogLevel

actual fun configureHttpClient() = configureHttpClient(OkHttp, 10_000L, LogLevel.ALL)
