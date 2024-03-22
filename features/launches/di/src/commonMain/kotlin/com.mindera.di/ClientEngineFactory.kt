package com.mindera.di

import io.ktor.client.HttpClient

interface ClientEngineFactory {
   fun  configureHttpClient(): HttpClient
}

expect fun getConfigureHttpClient(): ClientEngineFactory
