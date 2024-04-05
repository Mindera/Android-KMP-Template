package com.mindera.kmpexample.launches.domain.datasource.remote

import com.mindera.kmpexample.launches.domain.model.CurrencyExchangeResponseItem

interface CurrencyExchangeRemoteSource {

    suspend fun getCurrencyExchange(): List<CurrencyExchangeResponseItem>

}
