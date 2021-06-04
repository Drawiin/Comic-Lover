package com.example.comiclover.network.service

import com.example.comiclover.network.dto.ComicDto
import com.example.comiclover.network.dto.DataDto
import com.example.comiclover.network.dto.ResponseDto
import com.example.comiclover.network.util.Authenticator
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

class MarvelService @Inject constructor(
    private val client: HttpClient,
    private val authenticator: Authenticator
) {
    suspend fun getComics() = client.get<ResponseDto>("comics") {
        addCredentials()
        parameter("limit", 20)
        parameter("offset", 20)
    }

    private fun HttpRequestBuilder.addCredentials() {
        parameter("apikey", authenticator.credentials.apiKey)
        parameter("hash", authenticator.credentials.hash)
        parameter("ts", authenticator.credentials.timeStamp)
    }
}