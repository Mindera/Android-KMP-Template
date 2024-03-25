package com.mindera.coroutines.either

import com.mindera.coroutines.either.Either.Left
import com.mindera.coroutines.either.Either.Right
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind.AT_MOST_ONCE
import kotlin.contracts.contract
import kotlin.jvm.JvmName
import kotlin.native.HiddenFromObjC

@OptIn(ExperimentalContracts::class)
sealed class Either<out A, out B> {

    fun isLeft(): Boolean {
        contract {
            returns(true) implies (this@Either is Left<A>)
            returns(false) implies (this@Either is Right<B>)
        }
        return this@Either is Left<A>
    }

    fun isRight(): Boolean {
        contract {
            returns(true) implies (this@Either is Right<B>)
            returns(false) implies (this@Either is Left<A>)
        }
        return this@Either is Right<B>
    }

    inline fun isLeft(predicate: (A) -> Boolean): Boolean {
        contract { returns(true) implies (this@Either is Left<A>) }
        return this@Either is Left<A> && predicate(value)
    }

    inline fun isRight(predicate: (B) -> Boolean): Boolean {
        contract { returns(true) implies (this@Either is Right<B>) }
        return this@Either is Right<B> && predicate(value)
    }

    data class Left<out A> constructor(val value: A) : Either<A, Nothing>() {
        override fun toString(): String = "Either.Left($value)"
    }

    data class Right<out B> constructor(val value: B) : Either<Nothing, B>() {
        override fun toString(): String = "Either.Right($value)"
    }
}

inline fun <L, R> Either<L, R>.on(left: (L) -> Unit = {}, right: (R) -> Unit = {}) = when (this) {
    is Left -> left(value)
    is Right -> right(value)
}

@OptIn(ExperimentalContracts::class)
@HiddenFromObjC
inline fun <L, R> Either<L, R>.onLeft(action: (L) -> Unit = {}): Either<L, R> {
    contract {
        callsInPlace(action, AT_MOST_ONCE)
    }
    if (this is Left) action(value)
    return this
}

@OptIn(ExperimentalContracts::class)
@HiddenFromObjC
inline fun <L, R> Either<L, R>.onRight(action: (R) -> Unit = {}): Either<L, R> {
    contract {
        callsInPlace(action, AT_MOST_ONCE)
    }
    if (this is Right) action(value)
    return this
}

@HiddenFromObjC
@JvmName("ifEmptyList")
inline fun <L, R> Either<List<L>, R>.ifEmpty(
    block: () -> Either<List<L>, R>
): Either<List<L>, R> {
    if (this is Left && value.isNotEmpty()) return this
    return block()
}

@HiddenFromObjC
@JvmName("ifEmptyPairList")
inline fun <L, R> Either<Pair<List<L>, Boolean>, R>.ifEmpty(
    block: () -> Either<Pair<List<L>, Boolean>, R>
): Either<Pair<List<L>, Boolean>, R> {
    if (this is Left && value.first.isNotEmpty()) return this
    return block()
}

@HiddenFromObjC
@JvmName("ifEmptyFlowPairList")
inline fun <L, R> Flow<Either<Pair<List<L>, Boolean>, R>>.ifEmpty(
    crossinline block: () -> Either<Pair<List<L>, Boolean>, R>
): Flow<Either<Pair<List<L>, Boolean>, R>> = map { it.ifEmpty(block)}
