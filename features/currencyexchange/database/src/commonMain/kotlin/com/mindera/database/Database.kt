package com.mindera.database

import app.cash.sqldelight.db.SqlDriver
import com.mindera.currencyexchange.database.AppDatabase
import com.mindera.rest.CurrencyExchangeResponseItem
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Database(sqlDriver: SqlDriver) {
    private val database = AppDatabase(sqlDriver)
    private val dbQuery = database.appDatabaseQueries
    fun insertCurrency(currency: CurrencyExchangeResponseItem) {
        isCurrencyExist(currency.effectiveDate).ifEmpty {
            dbQuery.inserCurrencies(
                tableName = currency.table,
                no = currency.no,
                effectiveDate = currency.effectiveDate,
                rates =  Json.encodeToString(currency.rates)
            )
        }
    }

    private fun isCurrencyExist(effectiveDate: String): List<CurrencyExchangeResponseItem> =
        dbQuery.selectOneCurrency(effectiveDate = effectiveDate, ::mapCurrency).executeAsList()


    fun getAllCurrencies(): List<CurrencyExchangeResponseItem> {
        return dbQuery.selectAllCurrencies(::mapCurrency).executeAsList()
    }

    private fun mapCurrency(
        tableName: String,
        no: String,
        effectiveDate: String,
        rates: String
    ): CurrencyExchangeResponseItem {
        return CurrencyExchangeResponseItem(
            table = tableName,
            no = no,
            effectiveDate = effectiveDate,
            rates = Json.decodeFromString(rates)
        )
    }

    fun removeAllCurrencies() {
        dbQuery.transaction {
            dbQuery.removeAllCurrencies()
        }
    }


}
