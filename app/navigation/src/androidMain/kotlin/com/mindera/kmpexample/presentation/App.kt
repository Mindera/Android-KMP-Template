package com.mindera.kmpexample.presentation

import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.mindera.kmpexample.presentation.Destination.Main
import com.mindera.kmpexample.presentation.main.MainScene
import com.mindera.precompose.navigation.NavHost
import com.mindera.precompose.navigation.scene
import com.mindera.precompose.navigation.transition.NoTransition
import com.mindera.precompose.navigation.transition.defaultTransition
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.rememberNavigator

sealed class Destination(
    override val route: String
) : com.mindera.precompose.navigation.Destination {
    data object Main : Destination("/main")
}

@Composable
fun App(
    modifier: Modifier = Modifier,
    navigator: Navigator = rememberNavigator(),
    onBack: () -> Unit = {},
) {
    NavHost(
        modifier = modifier,
        navigator = navigator,
        initialRoute = Main,
        navTransition = remember { NoTransition },
    ) {
        scene(
            route = Main,
            navTransition = defaultTransition(pause = fadeOut()),
        ) {
            MainScene(onBack = onBack)
        }
    }
}
