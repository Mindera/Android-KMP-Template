package com.mindera.compose.collections

import androidx.compose.runtime.Immutable

@Immutable
data class ImmutableList<T> constructor(
    private val list: List<T> = emptyList(),
): List<T> by list

fun <T> List<T>.immutable(): ImmutableList<T> =
    if (this is ImmutableList) this else ImmutableList(this)
