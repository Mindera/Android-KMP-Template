package com.mindera.kmpexample.currencyexchange.viewmodel

import com.mindera.compose.collections.ImmutableList
import com.mindera.compose.collections.immutable
import com.mindera.coroutines.either.on
import com.mindera.kmpexample.domain.exceptions.Error
import com.mindera.kmpexample.currencyexchange.domain.model.CurrencyExchangeResponseItem
import com.mindera.kmpexample.currencyexchange.domain.usecase.GetCurrencyExchangeUseCase
import com.mindera.lifecycle.ViewModel
import com.mindera.lifecycle.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CurrencyExchangeViewModel constructor(
    private val getCurrencyExchange: GetCurrencyExchangeUseCase,
) : ViewModel() {
    data class CurrencyState(
        val loading: Boolean = true,
        val currencyExchange: ImmutableList<CurrencyExchangeResponseItem> = ImmutableList(),
        val error: Error? = null,
    )

    private var lastTenMutableStateFlow = MutableStateFlow(value = CurrencyState())
    val lastTenCurrencyStateFlow: StateFlow<CurrencyState>
        get() = lastTenMutableStateFlow

    private var currentMutableStateFlow = MutableStateFlow(value = CurrencyState())
    val currentCurrencyStateFlow: StateFlow<CurrencyState>
        get() = currentMutableStateFlow

    fun onChangeLastTen(provideNewState: ((CurrencyState) -> Unit)) {
        launch {
            lastTenMutableStateFlow.collect {
                provideNewState.invoke(it)
            }
        }
    }
    fun onChangeCurrentDay(provideNewState: ((CurrencyState) -> Unit)) {
        launch {
            currentMutableStateFlow.collect {
                provideNewState.invoke(it)
            }
        }
    }

// This example to access data in IOS
//class AppViewModel: ObservableObject {
//    let coreModel  = DIHelper().viewModel
//
//    init() {
//        coreModel.onChange { newState in
//                if newState.loading {
//                    print("Loading")
//                } else if newState.error != nil {
//                    print("Error: \(String(describing: newState.error))")
//                } else {
//                    print("Response: \(newState.currencyexchange)")
//                }
//        }
//    }
//}

    init {
        launch {
            getCurrencyExchange("A/last/10/").on(
                left = {
                    lastTenMutableStateFlow.value = with(lastTenMutableStateFlow.value) {
                        copy(
                            loading = false,
                            currencyExchange = it.immutable(),
                            error = null,
                        )
                    }
                },
                right = {
                    lastTenMutableStateFlow.value = with(lastTenMutableStateFlow.value) {
                        copy(
                            loading = false,
                            currencyExchange = ImmutableList(emptyList()),
                            error = it,
                        )
                    }
                },
            )

            getCurrencyExchange("A").on(
                left = {
                    currentMutableStateFlow.value = with(currentMutableStateFlow.value) {
                        copy(
                            loading = false,
                            currencyExchange = it.immutable(),
                            error = null,
                        )
                    }
                },
                right = {
                    currentMutableStateFlow.value = with(currentMutableStateFlow.value) {
                        copy(
                            loading = false,
                            currencyExchange = ImmutableList(emptyList()),
                            error = it,
                        )
                    }
                },
            )
        }
    }
}
