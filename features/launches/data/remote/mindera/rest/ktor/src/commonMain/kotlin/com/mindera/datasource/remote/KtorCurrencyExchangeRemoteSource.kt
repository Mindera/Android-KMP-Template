package com.mindera.datasource.remote

import com.mindera.datasource.remote.converters.toDomain
import com.mindera.kmpexample.launches.domain.datasource.remote.CurrencyExchangeRemoteSource
import com.mindera.rest.CurrencyExchangeResponseItem
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import com.mindera.kmpexample.launches.domain.model.CurrencyExchangeResponseItem as DomainCurrencyExchangeResponseItem

class KtorCurrencyExchangeRemoteSource constructor(
    private val baseUrl: String,
    private val client: HttpClient,
) : CurrencyExchangeRemoteSource {

    override suspend fun getCurrencyExchange(): List<DomainCurrencyExchangeResponseItem> =
        getCurrencyExchange("A").zip(getCurrencyExchange("B")) { listA, listB ->
            DomainCurrencyExchangeResponseItem(listA.rates + listB.rates)
        }

    private suspend fun getCurrencyExchange(table: String): List<DomainCurrencyExchangeResponseItem> =
        client.get("$baseUrl/$table")
            .body<List<CurrencyExchangeResponseItem>>().map {
                it.toDomain()
            }


}
