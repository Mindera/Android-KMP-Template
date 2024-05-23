package com.mindera.kmpexample.currencyexchange.usecase

import com.mindera.coroutines.either.Either
import com.mindera.kmpexample.domain.exceptions.Error
import com.mindera.kmpexample.currencyexchange.domain.datasource.remote.CurrencyExchangeRemoteSource
import com.mindera.kmpexample.currencyexchange.domain.model.CurrencyExchangeResponseItem
import com.mindera.kmpexample.currencyexchange.domain.usecase.GetCurrencyExchangeUseCase
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.withContext

/**
 * Returns Either<List<Launch>, Error>
 */
class GetCurrencyExchangeUseCaseV1 constructor(
    private val remote: CurrencyExchangeRemoteSource,
) : GetCurrencyExchangeUseCase {


    /**
     * Returns Either<List<CurrencyExchangeResponseItem>, Error>
     */
    override suspend fun invoke(endpoint: String): Either<List<CurrencyExchangeResponseItem>, Error> = withContext(Default) {
        try {
            Either.Left(remote.getCurrencyExchange(endpoint))
        } catch (e: Error) {
            println("API Error ${e}")
            if (remote.getAllCurrencies().isEmpty()) {
                Either.Right(e)
            } else {
                Either.Left(remote.getAllCurrencies())
            }
        }
    }
}
