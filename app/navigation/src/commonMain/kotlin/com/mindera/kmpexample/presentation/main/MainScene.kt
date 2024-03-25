package com.mindera.kmpexample.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.mindera.kmpexample.launches.CurrencyExchangeScene
import com.mindera.kmpexample.presentation.main.Destination.CurrencyExchange
import com.mindera.precompose.navigation.NavHost
import com.mindera.precompose.navigation.scene
import com.mindera.precompose.navigation.transition.NoTransition
import moe.tlaster.precompose.navigation.rememberNavigator

sealed class Destination(
    override val route: String,
) : com.mindera.precompose.navigation.Destination {
    data object CurrencyExchange : Destination("currency-exchange")
}

@Composable
fun MainScene(
    /* Callback */
    onBack: () -> Unit,
) {
    val navigator = rememberNavigator()

    NavHost(
        navTransition = remember { NoTransition },
        navigator = navigator,
        initialRoute = CurrencyExchange,
    ) {
        scene(route = CurrencyExchange) {
            CurrencyExchangeScene(onBack = onBack)
        }
    }
}
