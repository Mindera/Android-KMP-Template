package com.mindera.kmpexample

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.ui.Modifier
import com.mindera.isDarkMode
import com.mindera.kmpexample.currencyexchange.language.LocaleHelper
import com.mindera.kmpexample.currencyexchange.language.LocaleHelper.getLanguageSharedPreference
import com.mindera.kmpexample.presentation.App
import com.mindera.kmpexample.ui.theme.CurrencyExchangeTheme
import moe.tlaster.precompose.lifecycle.setContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CurrencyExchangeTheme(darkTheme = isDarkMode(this)) {
                App(
                    modifier = Modifier
                        .navigationBarsPadding()
                        .statusBarsPadding(),
                    onBack = { finish() },
                )
            }
        }
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(
            LocaleHelper.setLocale(newBase, getLanguageSharedPreference(newBase))
        )
    }
}
