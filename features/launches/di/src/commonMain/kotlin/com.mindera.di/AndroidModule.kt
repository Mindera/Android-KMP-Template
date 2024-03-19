package com.mindera.di

import com.mindera.client.configureHttpClient
import com.mindera.kmpexample.launches.viewmodel.CurrencyExchangeViewModel
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.logging.LogLevel
import org.koin.dsl.module

fun androidModule() = module {

    factory { configureHttpClient(OkHttp, 10_000L, LogLevel.ALL) }

    factory { CurrencyExchangeViewModel(getCurrencyExchange = get()) }

}
