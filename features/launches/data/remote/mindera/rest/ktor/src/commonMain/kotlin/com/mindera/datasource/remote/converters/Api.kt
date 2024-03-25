package com.mindera.datasource.remote.converters

import com.mindera.rest.CurrencyExchangeResponseItem
import com.mindera.uuid

//internal fun CurrencyExchangeResponseItem.toDomain(ratesItems: List<RatesItem>) = DomainCurrencyExchangeResponseItem(
//    rates = ratesItems,
//)
//
//internal fun com.mindera.rest.RatesItem.toRatesItemDomain() = RatesItem(
//    id = uuid(), // this is just a sample we usually don't need it as is
//    code = code,
//    currencyRate = currencyRate,
//    currency = currency,
//)

internal fun CurrencyExchangeResponseItem.toDomain() =
    com.mindera.kmpexample.launches.domain.model.CurrencyExchangeResponseItem(
        rates = rates.map { it.toDomain() },
    )

internal fun com.mindera.rest.RatesItem.toDomain() =
    com.mindera.kmpexample.launches.domain.model.RatesItem(
        id = uuid(), // this is just a sample we usually don't need it as is
        code = code,
        currencyRate = currencyRate,
        currency = currency,
    )
