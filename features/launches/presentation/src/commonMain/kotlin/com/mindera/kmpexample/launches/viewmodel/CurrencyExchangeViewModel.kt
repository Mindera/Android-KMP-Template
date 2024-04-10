package com.mindera.kmpexample.launches.viewmodel

import com.mindera.compose.collections.ImmutableList
import com.mindera.compose.collections.immutable
import com.mindera.coroutines.either.on
import com.mindera.kmpexample.domain.exceptions.Error
import com.mindera.kmpexample.launches.domain.model.CurrencyExchangeResponseItem
import com.mindera.kmpexample.launches.domain.usecase.GetCurrencyExchangeUseCase
import com.mindera.lifecycle.ViewModel
import com.mindera.lifecycle.launch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CurrencyExchangeViewModel constructor(
    private val getCurrencyExchange: GetCurrencyExchangeUseCase,
) : ViewModel() {
    data class CurrencyState(
        val loading: Boolean = true,
        val launches: ImmutableList<CurrencyExchangeResponseItem> = ImmutableList(),
        val error: Error? = null,
    )

    var mutableStateFlow = MutableStateFlow(value = CurrencyState())
        private set

    val currencyStateFlow: StateFlow<CurrencyState>
        get() = mutableStateFlow

    fun onChange(provideNewState: ((CurrencyState) -> Unit)) {
        currencyStateFlow.onEach {
            provideNewState.invoke(it)
        }.launchIn(CoroutineScope(Dispatchers.Main))
    }

// This example to access data in IOS
//    class AppViewModel: ObservableObject {
//        let coreModel : CurrencyExchangeViewModel = DIHelper().viewModel
//
//        func fetchData() {
//            coreModel.onChange { newState in
//                    print(newState.launches)
//            }
//        }
//    }

    init {
        launch {
            getCurrencyExchange("A/last/10/").on(
                left = {
                    println(">>> Rates: $it")
                    mutableStateFlow.value = with(mutableStateFlow.value) {
                        copy(
                            loading = false,
                            launches = it.immutable(),
                            error = null,
                        )
                    }
                },
                right = {
                    println(">>> Rates error: $it")
                    mutableStateFlow.value = with(mutableStateFlow.value) {
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
