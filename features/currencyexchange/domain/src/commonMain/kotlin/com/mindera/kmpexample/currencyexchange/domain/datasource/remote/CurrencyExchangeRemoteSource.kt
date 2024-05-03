package com.mindera.kmpexample.currencyexchange.domain.datasource.remote

import com.mindera.kmpexample.currencyexchange.domain.model.CurrencyExchangeResponseItem

interface CurrencyExchangeRemoteSource {

    suspend fun getCurrencyExchange(endpoint: String): List<CurrencyExchangeResponseItem>

}
