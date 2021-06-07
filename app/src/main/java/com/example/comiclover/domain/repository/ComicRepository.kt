package com.example.comiclover.domain.repository

import com.example.comiclover.core.arch.Either
import com.example.comiclover.core.error.Failure
import com.example.comiclover.domain.model.Comic

interface ComicRepository {
    suspend fun getComics(): Either<Failure, List<Comic>>
}