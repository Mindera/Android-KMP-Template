package com.mindera.datasource.remote

import app.cash.sqldelight.db.SqlDriver
import com.mindera.database.Database
import com.mindera.datasource.remote.converters.toDomain
import com.mindera.kmpexample.currencyexchange.domain.datasource.remote.CurrencyExchangeRemoteSource
import com.mindera.rest.CurrencyExchangeResponseItem
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import com.mindera.kmpexample.currencyexchange.domain.model.CurrencyExchangeResponseItem as DomainCurrencyExchangeResponseItem

class KtorCurrencyExchangeRemoteSource constructor(
    private val baseUrl: String,
    private val client: HttpClient,
    sqlDriver: SqlDriver
) : CurrencyExchangeRemoteSource {

    private val database = Database(sqlDriver)

    override suspend fun getCurrencyExchange(endpoint: String): List<DomainCurrencyExchangeResponseItem> {
        client.get("$baseUrl/$endpoint")
            .body<List<CurrencyExchangeResponseItem>>().map {
                insertCurrency(it)
            }
       return getAllCurrencies()
    }

    override fun insertCurrency(currency: CurrencyExchangeResponseItem) =
        database.insertCurrency(currency)


    override suspend fun getAllCurrencies(): List<DomainCurrencyExchangeResponseItem> =
        database.getAllCurrencies().map {
            it.toDomain()
        }


    override suspend fun removeAllCurrencies() =
        database.removeAllCurrencies()

}
