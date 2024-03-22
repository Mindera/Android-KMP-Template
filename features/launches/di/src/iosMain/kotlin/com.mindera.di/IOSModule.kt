package com.mindera.di

import org.koin.dsl.module

fun iosModule() = module {
    factory { getConfigureHttpClient().configureHttpClient() }
}
