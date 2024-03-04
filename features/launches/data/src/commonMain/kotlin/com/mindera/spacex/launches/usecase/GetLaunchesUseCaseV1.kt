package com.mindera.spacex.launches.usecase

import com.mindera.coroutines.either.Either
import com.mindera.spacex.domain.exceptions.Error
import com.mindera.spacex.launches.domain.datasource.remote.LaunchesRemoteSource
import com.mindera.spacex.launches.domain.model.Launch
import com.mindera.spacex.launches.domain.usecase.GetLaunchesUseCase
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
    override suspend fun invoke(): Either<List<Launch>, Error> = withContext(Default) {
        try {
            Either.Left(remote.getLaunches())
        } catch (e: Error) {
            Either.Right(e)
        }
    }
}
