package com.example.comiclover.network.service

import io.ktor.client.*
import javax.inject.Inject

class MarvelService @Inject constructor(private val client: HttpClient) {}