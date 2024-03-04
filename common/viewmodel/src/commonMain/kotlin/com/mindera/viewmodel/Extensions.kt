package com.mindera.viewmodel

import androidx.compose.runtime.Composable
import com.mindera.BundleOfArguments
import com.mindera.lifecycle.ViewModel
import kotlin.reflect.KClass

lateinit var factory: ViewModelProvider

interface ViewModelProvider {
    fun <T : ViewModel> create(
        klass: KClass<T>,
        bundle: BundleOfArguments?,
    ): T
}

fun <VM : ViewModel> create(
    klass: KClass<VM>,
    bundle: BundleOfArguments? = null,
): VM = factory.create(klass, bundle)

fun <VM : ViewModel> create(
    klass: KClass<VM>,
    vararg args: Pair<Any, Any?>,
): VM = create(klass, mapOf(pairs = args))

@Composable
fun <VM : ViewModel> viewModel(
    klass: KClass<VM>,
    bundle: BundleOfArguments? = null,
    keys: List<Any?> = emptyList(),
): VM = moe.tlaster.precompose.viewmodel.viewModel(klass, keys) { create(klass, bundle) }

@Composable
fun <VM : ViewModel> viewModel(
    klass: KClass<VM>,
    vararg args: Pair<Any, Any?>,
    keys: List<Any?> = emptyList(),
): VM = viewModel(klass, mapOf(pairs = args), keys)
