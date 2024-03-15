package com.mindera.kmpexample.launches.domain.datasource.remote

import com.mindera.kmpexample.launches.domain.model.CurrencyExchangeResponseItem
import com.mindera.rest.RatesItem
import com.mindera.kmpexample.launches.domain.model.RatesItem as DomainRatesItem

interface CurrencyExchangeRemoteSource {

    suspend fun getCurrencyExchange(): List<CurrencyExchangeResponseItem>

    suspend fun getCurrencyExchangeRates(item: RatesItem): DomainRatesItem
}
