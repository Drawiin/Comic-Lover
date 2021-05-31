package com.example.comiclover.network

import io.ktor.client.*
import io.ktor.client.request.*

class TodoApi(private val client: HttpClient) {
    suspend fun getTodo(): Todo = client.get<Todo>("https://jsonplaceholder.typicode.com/todos/1")
}