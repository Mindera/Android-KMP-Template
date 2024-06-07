package com.mindera.di

import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(iosModule(), appModule())
    }
}
