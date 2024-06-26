package com.mindera.kmpexample.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.mindera.kmpexample.currencyexchange.HomeScreenScene
import com.mindera.kmpexample.currencyexchange.opensource.OpensourceScene
import com.mindera.kmpexample.presentation.main.Destination.CurrencyExchange
import com.mindera.kmpexample.presentation.main.Destination.OpenSource
import com.mindera.precompose.navigation.NavHost
import com.mindera.precompose.navigation.scene
import com.mindera.precompose.navigation.transition.NoTransition
import moe.tlaster.precompose.navigation.rememberNavigator

sealed class Destination(
    override val route: String,
) : com.mindera.precompose.navigation.Destination {
    data object CurrencyExchange : Destination("currency-exchange")
    data object OpenSource : Destination("opensource")
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
            HomeScreenScene(onBack = onBack, navigator = navigator)
        }
        scene(route = OpenSource) {
            OpensourceScene(navigator = navigator)
        }
    }
}
