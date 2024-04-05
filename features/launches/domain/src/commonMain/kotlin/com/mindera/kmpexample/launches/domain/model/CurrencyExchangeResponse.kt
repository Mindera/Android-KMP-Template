package com.mindera.kmpexample.launches.domain.model

data class RatesItem(

	val id: String,

	val code: String,

	val currencyRate: Double,

	val currency: String
)

data class CurrencyExchangeResponseItem(

	val rates: List<RatesItem>,
)
