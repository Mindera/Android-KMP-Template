package com.mindera

import platform.Foundation.NSUUID

actual fun uuid(): String = "iOS ${NSUUID().UUIDString()}"
