package com.mindera.precompose.navigation

import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.PopUpTo
import moe.tlaster.precompose.navigation.PopUpTo.None

interface Destination {
    val route: String
}

fun <T> Destination.add(key: String, value: T) = route.add(key, value)

fun <T> String.add(key: String, value: T) = if (contains("{$key}"))
    replace("{$key}", value.toString())
else if (contains("?")) {
    plus("&$key=$value")
} else {
    plus("?$key=$value")
}

fun Navigator.navigate(
    route: Destination,
    options: NavOptions? = null,
) = navigate(route.route, options)

fun Navigator.navigate(
    route: Destination,
    launchSingleTop: Boolean = false,
    popUpTo: PopUpTo = None,
) = navigate(route.route, launchSingleTop, popUpTo)

fun Navigator.navigate(
    route: String,
    launchSingleTop: Boolean = false,
    popUpTo: PopUpTo = None,
) = navigate(route, NavOptions(launchSingleTop, popUpTo = popUpTo))
