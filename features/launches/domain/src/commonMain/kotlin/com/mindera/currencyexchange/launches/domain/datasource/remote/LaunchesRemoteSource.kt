package com.mindera.currencyexchange.launches.domain.datasource.remote

import com.mindera.currencyexchange.launches.domain.model.CurrencyExchangeResponseItem
import com.mindera.rest.RatesItem
import com.mindera.currencyexchange.launches.domain.model.RatesItem as DomainRatesItem

interface LaunchesRemoteSource {

    suspend fun getCurrencyExchange(): List<CurrencyExchangeResponseItem>

    suspend fun getCurrencyExchangeRates(item: RatesItem): DomainRatesItem
}
