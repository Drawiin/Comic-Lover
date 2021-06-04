package com.example.comiclover.network.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorDto(
    @SerialName("code")
    val code: Int,
    @SerialName("status")
    val status: String?,
    @SerialName("message")
    val message: String?
)