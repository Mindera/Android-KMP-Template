package com.mindera.datasource.remote.converters

import com.mindera.rest.CurrencyExchangeResponseItem
import com.mindera.uuid

internal fun CurrencyExchangeResponseItem.toDomain() =
    com.mindera.kmpexample.currencyexchange.domain.model.CurrencyExchangeResponseItem(
        table = table,
        no = no,
        effectiveDate = effectiveDate,
        rates = rates.map { it.toDomain() },
    )

internal fun com.mindera.rest.RatesItem.toDomain() =
    com.mindera.kmpexample.currencyexchange.domain.model.RatesItem(
        id = uuid(), // this is just a sample we usually don't need it as is
        code = code,
        currencyRate = currencyRate,
        currency = currency,
    )
