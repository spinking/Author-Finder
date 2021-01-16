package studio.eyesthetics.authorfinder.data.network.errors

import java.io.IOException

sealed class ApiError(override val message:String): IOException(message) {
    class BadRequest(message: String?) : ApiError(message?: "Bad Request")
    class NotFound(message: String? ) : ApiError(message ?: "Not found")
    class InternalServerError(message: String?) : ApiError(message ?: "Internal server error")
    class UnknownError(message: String?) : ApiError(message ?: "Unknown error" )
}

data class Error(
    val error: ErrorBody
)

data class ErrorBody(
    val status: String,
    val message:String
)