package com.example.comiclover.network.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseDto(
    @SerialName("code")
    val code: Int?,
    @SerialName("data")
    val dataDto: DataDto?
)