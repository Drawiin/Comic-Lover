package com.example.comiclover.features.main.data.repository

import com.drawiin.core.arch.Either
import com.example.comiclover.core.error.Failure
import com.example.comiclover.features.main.data.dto.AllCharactersDto
import com.example.comiclover.network.service.MarvelService
import com.example.comiclover.network.util.NetworkHandler
import com.example.comiclover.network.util.extensions.toCustomExceptions
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