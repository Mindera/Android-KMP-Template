package com.mindera

import java.util.UUID

actual fun uuid(): String = "Desktop ${UUID.randomUUID()}"
