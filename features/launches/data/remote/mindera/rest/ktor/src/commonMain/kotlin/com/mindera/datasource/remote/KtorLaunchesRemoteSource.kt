package com.mindera.datasource.remote

import com.mindera.currencyexchange.launches.domain.datasource.remote.LaunchesRemoteSource
import com.mindera.datasource.remote.converters.toDomain
import com.mindera.datasource.remote.converters.toRatesItemDomain
import com.mindera.rest.CurrencyExchangeResponseItem
import com.mindera.rest.RatesItem
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import com.mindera.currencyexchange.launches.domain.model.CurrencyExchangeResponseItem as DomainCurrencyExchangeResponseItem
import com.mindera.currencyexchange.launches.domain.model.RatesItem as DomainRates

class KtorLaunchesRemoteSource constructor(
    private val baseUrl: String,
    private val client: HttpClient,
): LaunchesRemoteSource {

    override suspend fun getCurrencyExchange(): List<DomainCurrencyExchangeResponseItem> =
        getCurrencyExchange("A").zip(getCurrencyExchange("B")) { listA, listB ->
            DomainCurrencyExchangeResponseItem(listA.rates + listB.rates)
        }

    private suspend fun getCurrencyExchange(table: String): List<DomainCurrencyExchangeResponseItem> = client.get("$baseUrl/$table")
        .body<List<CurrencyExchangeResponseItem>>().map {
            val list = ArrayList<DomainRates>()
            it.rates.forEach {rItem ->
                list.add(getCurrencyExchangeRates(rItem))
            }
            it.toDomain(list)
        }


    override suspend fun getCurrencyExchangeRates(item: RatesItem): DomainRates =
        item.toRatesItemDomain()

}
