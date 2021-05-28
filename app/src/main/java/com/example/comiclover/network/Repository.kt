package com.example.comiclover.network

class UserRepositoryImpl(
    private val networkHandler: NetworkHandler,
    private val userApi: UseApi
) {
    suspend fun getUserKtor(userId: String): Todo? =
        when (networkHandler.isConnected) {
            true -> {
                try {
                    userApi.getUserKtor()
                } catch (e: Exception) {
                    null
                }
            }
            else -> null
        }
}