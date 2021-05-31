package com.example.comiclover.repository

import com.example.comiclover.domain.boundaries.ComicRepository
import com.example.comiclover.network.service.MarvelService
import javax.inject.Inject

class DefaultComicRepository @Inject constructor(private val marvelService: MarvelService): ComicRepository {
}