package com.mindera.datasource.remote

import com.mindera.datasource.remote.converters.toDomain
import com.mindera.rest.Launch
import com.mindera.spacex.launches.domain.datasource.remote.LaunchesRemoteSource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import com.mindera.spacex.launches.domain.model.Launch as DomainLaunch

class KtorLaunchesRemoteSource constructor(
    private val baseUrl: String,
    private val client: HttpClient,
): LaunchesRemoteSource {
    override suspend fun getLaunches(): List<DomainLaunch> = client.get("$baseUrl/launches")
        .body<List<Launch>>().map { it.toDomain() }
}
