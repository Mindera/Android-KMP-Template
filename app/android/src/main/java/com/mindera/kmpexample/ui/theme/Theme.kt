package com.mindera.kmpexample.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Suppress("MagicNumber")
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF000000), // StatusBar
    onPrimary = Color(0xFFFFFFFF), // Text
    primaryContainer = Color(0xFF212121), // BarBackground
    onPrimaryContainer = Color(0xFF000000),// NavigationBarItem
    tertiary = Color(0xFFE0F1F3), // TabRow indicator
    secondary = Color(0xFF272F30), // Card Background
    secondaryContainer = Color(0xFFFFC107), // Line color
    background = Color(0xFF000000), // Background
    onBackground = Color(0xFF000000), // Graph Background
    surface = Color(0xFFFFC107), //Graph life
    onSurface = Color(0xFF000000), // Text on surface
)

@Suppress("MagicNumber")
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFFFFFFF), // StatusBar
    onPrimary = Color(0xFF000000),// Text
    primaryContainer = Color(0xFFf3f0e0), // BarBackground
    onPrimaryContainer = Color(0xFFe6e4c0),// NavigationBarItem
    tertiary = Color(0xFF000000), // TabRow indicator
    secondary = Color(0xFFFFFFFF), // Card Background
    secondaryContainer = Color(0xFFFFC107), // Line color
    background = Color(0xFFFFFFFF), // Background
    onBackground = Color(0xFFFFFFFF), // Graph Background
    surface = Color(0xFF1472FF), //Graph life
    onSurface = Color(0xFFFFFFFF),// Text on surface
)

@Composable
fun CurrencyExchangeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
