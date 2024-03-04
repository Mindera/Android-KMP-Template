package com.mindera.spacex.launches.domain.model

data class Launch(
    val id: String,
    val name: String,
    val launchDate: Long,
    val success: Boolean,
)
