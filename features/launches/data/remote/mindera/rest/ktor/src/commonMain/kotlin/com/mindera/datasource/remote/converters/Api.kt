package com.mindera.datasource.remote.converters

import com.mindera.currencyexchange.launches.domain.model.RatesItem
import com.mindera.rest.CurrencyExchangeResponseItem
import com.mindera.uuid
import com.mindera.currencyexchange.launches.domain.model.CurrencyExchangeResponseItem as DomainCurrencyExchangeResponseItem

internal fun CurrencyExchangeResponseItem.toDomain(ratesItems: List<RatesItem>) = DomainCurrencyExchangeResponseItem(
    rates = ratesItems,
)

internal fun com.mindera.rest.RatesItem.toRatesItemDomain() = RatesItem(
    id = uuid(), // this is just a sample we usually don't need it as is
    code = code,
    currencyRate = currencyRate,
    currency = currency,
)
