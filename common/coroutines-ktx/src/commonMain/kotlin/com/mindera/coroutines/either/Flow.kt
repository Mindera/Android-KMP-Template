package com.mindera.coroutines.either

import com.mindera.coroutines.either.Either.Left
import com.mindera.coroutines.either.Either.Right
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlin.native.HiddenFromObjC

/**
 * FIXME: investigate the possibility to not need to pass the onError function (for instance
 * by defining an interface with the onError method and forcing all errors to implement this
 * interface). To be addressed in MARP-833
 */
//https://youtrack.jetbrains.com/issue/KT-9742
@HiddenFromObjC
inline fun <A, reified B : Throwable> flowCatch(
    crossinline onError: (Throwable) -> B,
    crossinline block: suspend FlowCollector<Either<A, B>>.() -> Unit,
): Flow<Either<A, B>> = flow {
    try {
        block()
    } catch (error: Throwable) {
        emit(
            Right(
                if (error is B) {
                    error
                } else {
                    onError(error)
                }
            )
        )
    }
}

@HiddenFromObjC
suspend inline fun <T, reified B : Throwable> Flow<T>.asEitherFlow(
    crossinline onError: (Throwable) -> B
): Flow<Either<T, B>> =
    flowCatch(onError) {
        collect { emit(Left(it)) }
    }

@HiddenFromObjC
suspend inline fun <T, reified B : Throwable> asEither(
    onError: (Throwable) -> B,
    block: () -> T
): Either<T, B> =
    try {
        Left(block())
    } catch (error: Throwable) {
        Right(
            if (error is B) {
                error
            } else {
                onError(error)
            }
        )
    }
