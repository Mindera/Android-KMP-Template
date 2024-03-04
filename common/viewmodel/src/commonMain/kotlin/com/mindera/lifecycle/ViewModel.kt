package com.mindera.lifecycle

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.viewModelScope
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

open class ViewModel: moe.tlaster.precompose.viewmodel.ViewModel()

fun ViewModel.launch(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit,
) = viewModelScope.launch(context, start, block)

fun ViewModel.async(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit,
) = viewModelScope.async(
    context = context,
    start = start,
    block = block
)
