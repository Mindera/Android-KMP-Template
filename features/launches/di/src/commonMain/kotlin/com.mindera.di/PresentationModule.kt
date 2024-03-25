package com.mindera.di

import com.mindera.datasource.remote.KtorCurrencyExchangeRemoteSource
import com.mindera.kmpexample.launches.domain.datasource.remote.CurrencyExchangeRemoteSource
import com.mindera.kmpexample.launches.domain.usecase.GetCurrencyExchangeUseCase
import com.mindera.kmpexample.launches.usecase.GetCurrencyExchangeUseCaseV1
import org.koin.dsl.module

fun appModule() = module {

    factory { configureHttpClient() }

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

}
