package com.mindera.coroutines

import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.CoroutineStart.DEFAULT
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withTimeout
import kotlinx.datetime.Clock
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.native.HiddenFromObjC
import kotlin.time.Duration

@OptIn(DelicateCoroutinesApi::class)
@HiddenFromObjC
fun <T> async(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = DEFAULT,
    block: suspend CoroutineScope.() -> T
): Deferred<T> {
    return GlobalScope.async(context, start, block)
}

@OptIn(DelicateCoroutinesApi::class)
@HiddenFromObjC
fun launch(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job {
    return GlobalScope.launch(context, start, block)
}

suspend fun <T> suspendCoroutineWithTimeout(
    timeout: Duration,
    block: (CancellableContinuation<T>) -> Unit,
): T = withTimeout(timeout) { suspendCancellableCoroutine(block) }

suspend fun <R> delayAtLeast(timeToWait: Long = 1000, block: suspend () -> R): R {
    val start = Clock.System.now().toEpochMilliseconds()
    return block().apply {
        val timeElapsed = Clock.System.now().toEpochMilliseconds() - start
        if (timeElapsed < timeToWait) {
            delay(timeToWait - timeElapsed)
        }
    }
}
