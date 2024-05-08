@file:Suppress("unused")

package com.mindera.client

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory

object Darwin: HttpClientEngineFactory<HttpClientEngineConfig> by io.ktor.client.engine.darwin.Darwin
