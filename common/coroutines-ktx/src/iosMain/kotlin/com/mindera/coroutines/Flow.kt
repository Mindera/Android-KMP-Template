@file:Suppress("unused")

package com.mindera.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

interface Cancellable {
    fun cancel()
}

fun Flow<Any?>.collect(
    onEach: (value: Any?) -> Unit,
    onCompletion: (cause: Throwable?) -> Unit
): Cancellable = with(CoroutineScope(SupervisorJob() + Main)) {
    launch {
        try {
            collect(onEach)
            onCompletion(null)
        } catch (e: Throwable) {
            onCompletion(e)
        }
    }
    return object : Cancellable {
        override fun cancel() = cancel(null)
    }
}
