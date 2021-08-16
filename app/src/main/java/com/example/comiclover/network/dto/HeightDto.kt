package com.example.comiclover.network.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HeightDto(
    @SerialName("unity")
    val unity: String,
    @SerialName("value")
    val value: Double
)