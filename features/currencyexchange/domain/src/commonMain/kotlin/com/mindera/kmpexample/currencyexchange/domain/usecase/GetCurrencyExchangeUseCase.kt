package com.mindera.kmpexample.currencyexchange.domain.usecase

import com.mindera.coroutines.either.Either
import com.mindera.kmpexample.domain.exceptions.Error
import com.mindera.kmpexample.currencyexchange.domain.model.CurrencyExchangeResponseItem
import kotlin.native.ObjCName

@Suppress("FUN_INTERFACE_WITH_SUSPEND_FUNCTION")
fun interface GetCurrencyExchangeUseCase {
    @ObjCName("callAsFunction")
    suspend operator fun invoke(endpoint: String): Either<List<CurrencyExchangeResponseItem>, Error>
}
