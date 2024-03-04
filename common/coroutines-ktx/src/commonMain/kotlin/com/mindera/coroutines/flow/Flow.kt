package com.mindera.coroutines.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.transform
import kotlin.reflect.KClass

/**
 * Returns a flow containing only values that are instances of specified type [R].
 */
@Suppress("UNCHECKED_CAST")
fun <R: Any> Flow<*>.filterIsInstance(
    klass: KClass<*>,
): Flow<R> = filter { it == null || klass.isInstance(it) } as Flow<R>

/**
 * Returns a flow that invokes the given [action] **before** each value of the upstream flow is emitted downstream.
 */
fun <T> Flow<T>.onFirst(action: suspend (T) -> Unit): Flow<T> {
    var isFirst = true
    return transform { value ->
        if (isFirst) {
            action(value)
            isFirst = false
        }
        return@transform emit(value)
    }
}

/**
 * Returns a flow that will start immediately but ensure the first emission only happens after [timeMillis].
 */
fun <T> Flow<T>.delayed(
    timeMillis: Long = 1000
): Flow<T> = combine(flowOf(Unit).onStart { delay(timeMillis) }) { a, _ -> a }
