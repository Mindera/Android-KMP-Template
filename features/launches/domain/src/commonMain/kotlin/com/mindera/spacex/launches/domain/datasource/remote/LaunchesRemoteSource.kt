package com.mindera.spacex.launches.domain.datasource.remote

import com.mindera.spacex.launches.domain.model.Launch

interface LaunchesRemoteSource {
    suspend fun getLaunches(): List<Launch>
}
