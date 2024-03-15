package com.mindera.kmpexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.ui.Modifier
import com.mindera.kmpexample.presentation.App
import com.mindera.kmpexample.ui.theme.CurrencyExchangeTheme
import moe.tlaster.precompose.lifecycle.setContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CurrencyExchangeTheme {
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
