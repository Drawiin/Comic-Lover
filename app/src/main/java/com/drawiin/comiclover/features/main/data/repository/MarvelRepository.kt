package com.drawiin.comiclover.features.main.data.repository

import com.drawiin.core.arch.Either
import com.drawiin.core.error.Failure
import com.drawiin.comiclover.features.main.data.dto.AllCharactersDto
import com.drawiin.comiclover.features.main.data.service.MarvelService
import com.drawiin.network.util.error.NetworkHandler
import com.drawiin.network.util.extensions.toCustomExceptions
import javax.inject.Inject

class MarvelRepository @Inject constructor(
    private val service: MarvelService,
    private val networkHandler: NetworkHandler
) {
    suspend fun getAllCharacters(): Either<Failure, AllCharactersDto> =
        when (networkHandler.isConnected) {
            true -> try {
                Either.Right(service.getAllCharacters())
            } catch (e: Exception) {
                Either.Left(e.toCustomExceptions())
            }
            else -> Either.Left(Failure.NetworkConnection)
        }
}