package com.mindera.rest

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Launch(
    @SerialName("mission_name") val missionName: String,
    @SerialName("launch_date_unix") val launchDate: Long,
    @SerialName("launch_success") val launchSuccess: Boolean?
)
