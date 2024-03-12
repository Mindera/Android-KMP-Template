package com.mindera.currencyexchange.launches.domain.usecase

import com.mindera.coroutines.either.Either
import com.mindera.currencyexchange.domain.exceptions.Error
import com.mindera.currencyexchange.launches.domain.model.CurrencyExchangeResponseItem
import kotlin.native.ObjCName

@Suppress("FUN_INTERFACE_WITH_SUSPEND_FUNCTION")
fun interface GetCurrencyExchangeUseCase {
    @ObjCName("callAsFunction")
    suspend operator fun invoke(): Either<List<CurrencyExchangeResponseItem>, Error>
}
