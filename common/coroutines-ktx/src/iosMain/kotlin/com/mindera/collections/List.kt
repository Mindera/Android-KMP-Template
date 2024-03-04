package com.mindera.collections

fun asArray(element: Any): List<*>? {
    if (element is List<*>) return element
    return null
}

fun Any.toArray(): List<*>? {
    if (this is List<*>) return this
    return null
}
