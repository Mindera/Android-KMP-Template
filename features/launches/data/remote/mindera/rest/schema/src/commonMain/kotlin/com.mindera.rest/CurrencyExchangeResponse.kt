package com.mindera.rest

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RatesItem(

	@SerialName("code")
	val code: String,

	@SerialName("mid")
	val currencyRate: Double,

	@SerialName("currency")
	val currency: String
)

@Serializable
data class CurrencyExchangeResponseItem(

	@SerialName("rates")
	val rates: List<RatesItem>,
)
