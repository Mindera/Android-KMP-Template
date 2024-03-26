package com.mindera.di

import com.mindera.kmpexample.launches.viewmodel.CurrencyExchangeViewModel
import org.koin.dsl.module


fun androidModule() = module {

    factory { CurrencyExchangeViewModel(getCurrencyExchange = get()) }

}
