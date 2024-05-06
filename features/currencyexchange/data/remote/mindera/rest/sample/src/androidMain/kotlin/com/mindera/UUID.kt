package com.mindera

import java.util.UUID

actual fun uuid(): String = "Android ${UUID.randomUUID()}"
