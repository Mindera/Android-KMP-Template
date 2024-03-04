package com.mindera.precompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

// FIXME: overloading issues

@Composable
fun BackHandler(
    enabled: MutableState<Boolean> = remember { mutableStateOf(true) },
    onBack: (enabled: MutableState<Boolean>) -> Unit,
) {
    if (enabled.value) {
        moe.tlaster.precompose.navigation.BackHandler {
            if (enabled.value) {
                enabled.value = false
                onBack(enabled)
            }
        }
    }
}

@Composable
fun BackHandler(
    enabled: MutableState<Boolean> = remember { mutableStateOf(true) },
    onBack: () -> Unit,
) {
    BackHandler(
        enabled = enabled,
        onBack = { _ -> onBack() }
    )
}
