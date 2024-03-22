package com.mindera.kmpexample.di

import com.mindera.di.getConfigureHttpClient
import com.mindera.kmpexample.launches.viewmodel.CurrencyExchangeViewModel
import org.koin.dsl.module


fun androidModule() = module {

    factory { getConfigureHttpClient().configureHttpClient() }

    factory { CurrencyExchangeViewModel(getCurrencyExchange = get()) }

}
