package com.mindera.kmpexample.di

import android.app.Application
import com.mindera.di.androidModule
import com.mindera.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DIApp : Application() {

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
