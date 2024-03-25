package com.mindera.di

import com.mindera.kmpexample.launches.domain.usecase.GetCurrencyExchangeUseCase
import com.mindera.kmpexample.launches.usecase.GetCurrencyExchangeUseCaseV1
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DIHelper: KoinComponent {
    val getGetCurrencyExchangeUseCase: GetCurrencyExchangeUseCase by inject()
}
