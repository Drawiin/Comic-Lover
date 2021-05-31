package com.example.comiclover.network

class UserRepositoryImpl(
    private val networkHandler: NetworkHandler,
    private val todoApi: TodoApi
) {
    suspend fun getUserKtor(): Todo = todoApi.getTodo()
}