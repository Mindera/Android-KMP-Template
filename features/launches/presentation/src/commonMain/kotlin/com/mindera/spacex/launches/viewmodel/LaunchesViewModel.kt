package com.mindera.spacex.launches.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.mindera.compose.collections.ImmutableList
import com.mindera.compose.collections.immutable
import com.mindera.coroutines.either.on
import com.mindera.lifecycle.ViewModel
import com.mindera.lifecycle.launch
import com.mindera.spacex.domain.exceptions.Error
import com.mindera.spacex.launches.domain.model.Launch
import com.mindera.spacex.launches.domain.usecase.GetLaunchesUseCase
import kotlinx.coroutines.flow.MutableStateFlow

class LaunchesViewModel constructor(
    private val getLaunches: GetLaunchesUseCase,
) : ViewModel() {
    data class State(
        val loading: Boolean = true,
        val launches: ImmutableList<Launch> = ImmutableList(),
        val error: Error? = null,
    )

    var state = MutableStateFlow(value = State())
        private set

    init {
        launch {
            getLaunches().on(
                left = {
                    println(">>> Launches: $it")
                    state.value = with(state.value) {
                        copy(
                            loading = false,
                            launches = it.immutable(),
                            error = null,
                        )
                    }
                },
                right = {
                    println(">>> Launches error: $it")
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
