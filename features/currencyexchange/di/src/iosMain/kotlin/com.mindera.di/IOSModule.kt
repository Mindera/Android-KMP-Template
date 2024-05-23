package com.mindera.di

import com.mindera.database.IOSDatabaseDriverFactory
import org.koin.dsl.module


fun iosModule() = module {
    factory { IOSDatabaseDriverFactory().createDriver() }
}
