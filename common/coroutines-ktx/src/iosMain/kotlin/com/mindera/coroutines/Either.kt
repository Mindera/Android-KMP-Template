package com.mindera.coroutines

import com.mindera.coroutines.either.Either
import com.mindera.coroutines.either.Either.Left
import com.mindera.coroutines.either.Either.Right

fun isLeft(either: Any) = when (either) {
    is Either<*, *> -> either.isLeft()
    else -> throw RuntimeException("Not a valid Either")
}

fun isRight(either: Any) = when (either) {
    is Either<*, *> -> either.isRight()
    else -> throw RuntimeException("Not a valid Either")
}

fun left(either: Any) = when (either) {
    is Left<*> -> either.value
    else -> null
}

fun right(either: Any) = when (either) {
    is Right<*> -> either.value
    else -> null
}
