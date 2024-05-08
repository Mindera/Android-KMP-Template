@file:Suppress("unused")

package com.mindera.precompose.navigation

import moe.tlaster.precompose.navigation.BackStackEntry
import moe.tlaster.precompose.navigation.QueryString

/**
 * These functions are a duplicate from the precompose lib.
 * Cannot inline bytecode from a more recent target into bytecode for an older target.
 */

inline fun <reified T> convertValue(value: String): T? {
    return when (T::class) {
        Int::class -> value.toIntOrNull()
        Long::class -> value.toLongOrNull()
        String::class -> value
        Boolean::class -> value.toBooleanStrictOrNull()
        Float::class -> value.toFloatOrNull()
        Double::class -> value.toDoubleOrNull()
        else -> throw NotImplementedError()
    } as T
}

inline fun <reified T> BackStackEntry.path(
    path: String,
    default: T? = null,
): T? {
    val value = pathMap[path] ?: return default
    return convertValue(value)
}

inline fun <reified T> QueryString.query(name: String, default: T? = null): T? {
    val value = map[name]?.firstOrNull() ?: return default
    return convertValue(value)
}

inline fun <reified T> BackStackEntry.query(
    path: String,
    default: T? = null,
): T? = queryString?.query(path, default)

inline fun <reified T> BackStackEntry.query(
    path: String,
    default: () -> T?,
): T? = query(path) ?: default()
