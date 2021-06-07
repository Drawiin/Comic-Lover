package com.example.comiclover.repository

import com.example.comiclover.core.arch.Either
import com.example.comiclover.core.error.Failure
import com.example.comiclover.core.extensions.toCustomExceptions
import com.example.comiclover.domain.model.Comic
import com.example.comiclover.domain.repository.ComicRepository
import com.example.comiclover.network.dto.toDomain
import com.example.comiclover.network.service.MarvelService
import com.example.comiclover.network.util.NetworkHandler
import javax.inject.Inject

class DefaultComicRepository @Inject constructor(
    private val marvelService: MarvelService,
    private val networkHandler: NetworkHandler
) : ComicRepository {
    override suspend fun getComics(): Either<Failure, List<Comic>> =
        when (networkHandler.isConnected) {
            true -> try {
                Either.Right(
                    marvelService.getComics()
                        .data
                        ?.results
                        ?.map { it.toDomain() }
                        ?: emptyList()
                )
            } catch (e: Exception) {
                Either.Left(e.toCustomExceptions())
            }

            else -> Either.Left(Failure.NetworkConnection)
        }
}
