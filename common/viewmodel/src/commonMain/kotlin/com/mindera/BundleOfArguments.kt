package com.mindera

typealias BundleOfArguments = Map<@JvmSuppressWildcards Any, @JvmSuppressWildcards Any?>
fun BundleOfArguments.getString(key: Any) = this[key]?.toString().orEmpty()
fun BundleOfArguments.getInt(key: Any) = getString(key).toInt()
fun BundleOfArguments.getBoolean(key: Any) = getString(key).toBoolean()
