package com.mindera.kmpexample.launches.usecase

import com.mindera.coroutines.either.Either
import com.mindera.kmpexample.domain.exceptions.Error
import com.mindera.kmpexample.launches.domain.datasource.remote.CurrencyExchangeRemoteSource
import com.mindera.kmpexample.launches.domain.model.CurrencyExchangeResponseItem
import com.mindera.kmpexample.launches.domain.usecase.GetCurrencyExchangeUseCase
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
    override suspend fun invoke(): Either<List<CurrencyExchangeResponseItem>, Error> = withContext(Default) {
        try {
            Either.Left(remote.getCurrencyExchange())
        } catch (e: Error) {
            Either.Right(e)
        }
    }

    fun testFunction() = "Test DI"
}
