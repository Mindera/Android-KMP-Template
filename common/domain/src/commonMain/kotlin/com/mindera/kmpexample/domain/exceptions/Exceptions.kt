package com.mindera.kmpexample.domain.exceptions

sealed class Error(
    override val message: String? = null,
    override val cause: Throwable? = null,
) : Throwable(message, cause) {
    companion object {
        fun onError(throwable: Throwable): Error =
            Generic(
                message = throwable.message,
                cause = throwable.cause
            )
    }

    data class Generic(
        override val message: String? = null,
        override val cause: Throwable? = null,
    ) : Error(message, cause)

    data class Network(
        override val message: String? = null,
        override val cause: Throwable? = null,
    ) : Error(message, cause)

    data class Unauthorized(
        override val message: String? = null,
        override val cause: Throwable? = null,
    ) : Error(message, cause)

    data class NotFound(
        override val message: String? = null,
        override val cause: Throwable? = null,
    ) : Error(message, cause)
}
