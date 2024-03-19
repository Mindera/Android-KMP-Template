package com.mindera.di

import com.mindera.client.Darwin
import com.mindera.client.configureHttpClient
import io.ktor.client.plugins.logging.LogLevel
import org.koin.dsl.module

fun iosModule() = module {

    factory { configureHttpClient(Darwin, 10_000L, LogLevel.ALL) }

}
