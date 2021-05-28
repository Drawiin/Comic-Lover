package com.example.comiclover.network

import io.ktor.client.*
import io.ktor.client.request.*

class UseApi(private val client: HttpClient) {
    suspend fun getUserKtor(): Todo = client.get("https://jsonplaceholder.typicode.com/todos/1")
}