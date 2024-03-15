package com.mindera.kmpexample.launches.viewmodel

import com.mindera.compose.collections.ImmutableList
import com.mindera.compose.collections.immutable
import com.mindera.coroutines.either.on
import com.mindera.kmpexample.domain.exceptions.Error
import com.mindera.kmpexample.launches.domain.model.CurrencyExchangeResponseItem
import com.mindera.kmpexample.launches.domain.usecase.GetCurrencyExchangeUseCase
import com.mindera.lifecycle.ViewModel
import com.mindera.lifecycle.launch
import kotlinx.coroutines.flow.MutableStateFlow

class CurrencyExchangeViewModel constructor(
    private val getCurrencyExchange: GetCurrencyExchangeUseCase,
) : ViewModel() {
    data class State(
        val loading: Boolean = true,
        val launches: ImmutableList<CurrencyExchangeResponseItem> = ImmutableList(),
        val error: Error? = null,
    )

    var state = MutableStateFlow(value = State())
        private set

    init {
        launch {
            getCurrencyExchange().on(
                left = {
                    println(">>> Rates: $it")
                    state.value = with(state.value) {
                        copy(
                            loading = false,
                            launches = it.immutable(),
                            error = null,
                        )
                    }
                },
                right = {
                    println(">>> Rates error: $it")
                    state.value = with(state.value) {
                        copy(
                            loading = false,
                            launches = ImmutableList(emptyList()),
                            error = it,
                        )
                    }
                },
            )
        }
    }
}
