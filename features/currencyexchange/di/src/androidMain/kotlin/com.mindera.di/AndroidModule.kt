package com.mindera.di

import com.mindera.database.AndroidDatabaseDriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


fun androidModule() = module {
    factory { AndroidDatabaseDriverFactory(context = androidContext()).createDriver() }
}
