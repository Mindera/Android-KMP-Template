package com.mindera.di

import com.mindera.datasource.remote.KtorCurrencyExchangeRemoteSource
import com.mindera.kmpexample.currencyexchange.domain.datasource.remote.CurrencyExchangeRemoteSource
import com.mindera.kmpexample.currencyexchange.domain.usecase.GetCurrencyExchangeUseCase
import com.mindera.kmpexample.currencyexchange.usecase.GetCurrencyExchangeUseCaseV1
import com.mindera.kmpexample.currencyexchange.viewmodel.CurrencyExchangeViewModel
import org.koin.dsl.module

fun appModule() = module {

    factory { configureHttpClient() }

    factory { CurrencyExchangeViewModel(getCurrencyExchange = get()) }

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
