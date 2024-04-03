package com.mindera.precompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import moe.tlaster.precompose.navigation.BackStackEntry
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.RouteBuilder
import moe.tlaster.precompose.navigation.SwipeProperties
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition
import moe.tlaster.precompose.stateholder.LocalStateHolder
import moe.tlaster.precompose.stateholder.StateHolder

private fun RouteBuilder.sceneInternal(
    route: String,
    deepLinks: List<String> = emptyList(),
    navTransition: NavTransition? = null,
    swipeProperties: SwipeProperties? = null,
    content: @Composable (State<BackStackEntry>) -> Unit,
) = scene(
    route = route,
    deepLinks = deepLinks,
    navTransition = navTransition,
    swipeProperties = swipeProperties,
    content = { content(remember { mutableStateOf(it) }) },
)

private fun RouteBuilder.scene(
    viewModelStoreOwner: StateHolder,
    route: String,
    deepLinks: List<String> = emptyList(),
    navTransition: NavTransition? = null,
    swipeProperties: SwipeProperties? = null,
    content: @Composable (BackStackEntry) -> Unit,
) {
    sceneInternal(
        route = route,
        deepLinks = deepLinks,
        navTransition = navTransition,
        swipeProperties = swipeProperties,
    ) {
        CompositionLocalProvider(
            LocalStateHolder provides viewModelStoreOwner,
        ) { content(it.value) }
    }
}

fun RouteBuilder.scene(
    viewModelStoreOwner: StateHolder,
    route: Destination,
    deepLinks: List<String> = emptyList(),
    navTransition: NavTransition? = null,
    swipeProperties: SwipeProperties? = null,
    content: @Composable (BackStackEntry) -> Unit,
) = scene(
    viewModelStoreOwner = viewModelStoreOwner,
    route = route.route,
    deepLinks = deepLinks,
    navTransition = navTransition,
    swipeProperties = swipeProperties,
    content = content,
)

fun RouteBuilder.scene(
    route: Destination,
    deepLinks: List<String> = emptyList(),
    navTransition: NavTransition? = null,
    swipeProperties: SwipeProperties? = null,
    content: @Composable (BackStackEntry) -> Unit,
) = sceneInternal(
    route = route.route,
    deepLinks = deepLinks,
    navTransition = navTransition,
    swipeProperties = swipeProperties,
    content = { content(it.value) },
)

/**
 * When we send a stateHolder to a scene and then try to retrieve the navigator from the new
 * StateHolder, when going back and coming in again, the navHost seems to be lost from composition.
 * This doesn't happen if the navigator is retrieved first and only then we apply the new stateHolder
 */
fun RouteBuilder.sceneHost(
    viewModelStoreOwner: StateHolder,
    route: String,
    deepLinks: List<String> = emptyList(),
    navTransition: NavTransition? = null,
    swipeProperties: SwipeProperties? = null,
    content: @Composable (Navigator, BackStackEntry) -> Unit,
) {
    sceneInternal(
        route = route,
        deepLinks = deepLinks,
        navTransition = navTransition,
        swipeProperties = swipeProperties,
    ) {
        val navigator = rememberNavigator()
        CompositionLocalProvider(
            LocalStateHolder provides viewModelStoreOwner,
        ) {
            content(navigator, it.value)
        }
    }
}

fun RouteBuilder.sceneHost(
    viewModelStoreOwner: StateHolder,
    route: Destination,
    deepLinks: List<String> = emptyList(),
    navTransition: NavTransition? = null,
    swipeProperties: SwipeProperties? = null,
    content: @Composable (Navigator, BackStackEntry) -> Unit,
) {
    sceneHost(
        viewModelStoreOwner = viewModelStoreOwner,
        route = route.route,
        deepLinks = deepLinks,
        navTransition = navTransition,
        swipeProperties = swipeProperties,
        content = content
    )
}
