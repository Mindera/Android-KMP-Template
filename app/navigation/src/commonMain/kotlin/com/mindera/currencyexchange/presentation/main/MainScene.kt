package com.mindera.currencyexchange.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.mindera.currencyexchange.launches.LaunchesScene
import com.mindera.currencyexchange.presentation.main.Destination.Launches
import com.mindera.precompose.navigation.NavHost
import com.mindera.precompose.navigation.scene
import com.mindera.precompose.navigation.transition.NoTransition
import moe.tlaster.precompose.navigation.rememberNavigator

sealed class Destination(
    override val route: String,
) : com.mindera.precompose.navigation.Destination {
    data object Launches : Destination("launches")
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
        initialRoute = Launches,
    ) {
        scene(route = Launches) {
            LaunchesScene(onBack = onBack)
        }
    }
}