package com.drawiin.comiclover.network.util.extensions

import com.drawiin.core.error.Failure
import io.ktor.client.features.*

fun Exception.toCustomExceptions() = when (this) {
    is ServerResponseException -> Failure.ServerFailure(this)
    is ClientRequestException ->
        when (this.response.status.value) {
            400 -> Failure.HttpErrorBadRequest(this)
            401 -> Failure.HttpErrorUnauthorized(this)
            403 -> Failure.HttpErrorForbidden(this)
            404 -> Failure.HttpErrorNotFound(this)
            else -> Failure.HttpError(this)
        }
    is RedirectResponseException -> Failure.HttpError(this)
    else -> Failure.UnexpectedError(this)
}