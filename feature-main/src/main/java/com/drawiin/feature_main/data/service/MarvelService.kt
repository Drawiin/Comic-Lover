package com.drawiin.feature_main.data.service

import com.drawiin.comiclover.features.main.data.dto.AllCharactersDto
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

class MarvelService @Inject constructor(
    private val client: HttpClient,
) {
    suspend fun getAllCharacters() = client.get<AllCharactersDto>(MarvelEndpoints.AllCharacters.location)
}

sealed class MarvelEndpoints(val location: String) {
    object AllCharacters : MarvelEndpoints("assets/application.json")
}