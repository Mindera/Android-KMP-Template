package com.mindera.currencyexchange.launches.viewmodel

import com.mindera.compose.collections.ImmutableList
import com.mindera.compose.collections.immutable
import com.mindera.coroutines.either.on
import com.mindera.currencyexchange.domain.exceptions.Error
import com.mindera.currencyexchange.launches.domain.model.CurrencyExchangeResponseItem
import com.mindera.currencyexchange.launches.domain.usecase.GetLaunchesUseCase
import com.mindera.lifecycle.ViewModel
import com.mindera.lifecycle.launch
import kotlinx.coroutines.flow.MutableStateFlow

class LaunchesViewModel constructor(
    private val getLaunches: GetLaunchesUseCase,
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
            getLaunches().on(
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
