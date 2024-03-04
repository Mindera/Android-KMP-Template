package com.mindera.datasource.remote.converters

import com.mindera.spacex.launches.domain.model.Launch
import com.mindera.uuid

internal fun com.mindera.rest.Launch.toDomain() = Launch(
    id = uuid(), // this is just a sample we usually don't need it as is
    launchDate = launchDate,
    name = missionName,
    success = launchSuccess ?: false,
)
