package com.mindera.precompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.RouteBuilder
import moe.tlaster.precompose.navigation.transition.NavTransition

@Composable
fun NavHost(
    modifier: Modifier = Modifier,
    navigator: Navigator,
    initialRoute: Destination,
    navTransition: NavTransition = remember { NavTransition() },
    builder: RouteBuilder.() -> Unit,
) = NavHost(
    modifier = modifier,
    navigator = navigator,
    initialRoute = initialRoute.route,
    navTransition = navTransition,
    builder = builder
)
