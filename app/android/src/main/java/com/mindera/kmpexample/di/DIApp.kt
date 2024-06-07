package com.mindera.kmpexample.di

import com.mindera.di.androidModule
import com.mindera.di.appModule
import com.mindera.kmpexample.currencyexchange.language.LanguageApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DIApp : LanguageApp() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DIApp)
            modules(
                listOf(androidModule(), appModule())
            )
        }
    }
}
