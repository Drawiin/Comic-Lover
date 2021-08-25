package com.drawiin.core.error

sealed class Failure {
    object NetworkConnection : Failure()
    data class ServerFailure(val e: Exception) : Failure()
    data class HttpErrorBadRequest(val e: Exception) : Failure()
    data class HttpErrorUnauthorized(val e: Exception) : Failure()
    data class HttpErrorForbidden(val e: Exception) : Failure()
    data class HttpErrorNotFound(val e: Exception) : Failure()
    data class HttpError(val e: Exception) : Failure()
    data class UnexpectedError(val e: Exception) : Failure()
}