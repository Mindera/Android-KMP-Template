package com.mindera.di

import com.mindera.kmpexample.launches.viewmodel.CurrencyExchangeViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object DIHelper: KoinComponent {
    val viewModel: CurrencyExchangeViewModel by inject()
}
