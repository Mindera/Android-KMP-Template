package com.mindera.spacex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.ui.Modifier
import com.mindera.spacex.presentation.App
import com.mindera.spacex.ui.theme.SpaceXTheme
import moe.tlaster.precompose.lifecycle.setContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpaceXTheme {
                App(
                    modifier = Modifier
                        .navigationBarsPadding()
                        .statusBarsPadding(),
                    onBack = { finish() },
                )
            }
        }
    }
}
