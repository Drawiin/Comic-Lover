package com.example.comiclover.domain.interactor

import com.example.comiclover.domain.repository.ComicRepository
import javax.inject.Inject

class GetMovies @Inject constructor(
    private val repository: ComicRepository
) {
    suspend operator fun invoke() = repository.getComics()
}