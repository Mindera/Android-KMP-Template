package com.mindera.kmpexample.currencyexchange.domain.datasource.remote

import com.mindera.kmpexample.currencyexchange.domain.model.CurrencyExchangeResponseItem as DomainCurrencyExchangeResponseItem
import com.mindera.rest.CurrencyExchangeResponseItem

interface CurrencyExchangeRemoteSource {

    suspend fun getCurrencyExchange(endpoint: String): List<DomainCurrencyExchangeResponseItem>

    fun insertCurrency(currency: CurrencyExchangeResponseItem)


    suspend fun getAllCurrencies(): List<DomainCurrencyExchangeResponseItem>

    suspend fun removeAllCurrencies()


}
