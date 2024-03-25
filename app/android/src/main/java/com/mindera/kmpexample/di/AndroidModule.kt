package com.mindera.kmpexample.di

import com.mindera.di.configureHttpClient
import com.mindera.kmpexample.launches.viewmodel.CurrencyExchangeViewModel
import org.koin.dsl.module


fun androidModule() = module {

    factory { configureHttpClient() }

    factory { CurrencyExchangeViewModel(getCurrencyExchange = get()) }

}
