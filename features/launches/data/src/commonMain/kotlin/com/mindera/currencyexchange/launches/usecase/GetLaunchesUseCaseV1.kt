package com.mindera.currencyexchange.launches.usecase

import com.mindera.coroutines.either.Either
import com.mindera.currencyexchange.domain.exceptions.Error
import com.mindera.currencyexchange.launches.domain.datasource.remote.LaunchesRemoteSource
import com.mindera.currencyexchange.launches.domain.model.CurrencyExchangeResponseItem
import com.mindera.currencyexchange.launches.domain.usecase.GetLaunchesUseCase
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.withContext

/**
 * Returns Either<List<Launch>, Error>
 */
class GetLaunchesUseCaseV1 constructor(
    private val remote: LaunchesRemoteSource,
) : GetLaunchesUseCase {

    /**
     * Returns Either<List<Launch>, Error>
     */
    override suspend fun invoke(): Either<List<CurrencyExchangeResponseItem>, Error> = withContext(Default) {
        try {
            Either.Left(remote.getCurrencyExchange())
        } catch (e: Error) {
            Either.Right(e)
        }
    }
}
