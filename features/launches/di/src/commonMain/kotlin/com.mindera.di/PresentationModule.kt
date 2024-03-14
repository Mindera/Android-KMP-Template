package com.mindera.di

import com.mindera.client.configureHttpClient
import com.mindera.currencyexchange.launches.domain.datasource.remote.CurrencyExchangeRemoteSource
import com.mindera.currencyexchange.launches.domain.usecase.GetCurrencyExchangeUseCase
import com.mindera.currencyexchange.launches.usecase.GetCurrencyExchangeUseCaseV1
import com.mindera.currencyexchange.launches.viewmodel.CurrencyExchangeViewModel
import com.mindera.datasource.remote.KtorCurrencyExchangeRemoteSource
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.logging.LogLevel
import org.koin.dsl.module

fun appModule() = module {

    factory { configureHttpClient(OkHttp, 10_000L, LogLevel.ALL) }

    single<CurrencyExchangeRemoteSource> {
        KtorCurrencyExchangeRemoteSource(
            baseUrl = "https://api.nbp.pl/api/exchangerates/tables",
            client = get()
        )
    }

    single<GetCurrencyExchangeUseCase> {
        GetCurrencyExchangeUseCaseV1(
            remote = get()
        )
    }

    factory { CurrencyExchangeViewModel(getCurrencyExchange = get()) }

}
