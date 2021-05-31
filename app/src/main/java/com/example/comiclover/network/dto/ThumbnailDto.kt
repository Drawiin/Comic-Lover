package com.example.comiclover.network.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ThumbnailDto(
    @SerialName("extension")
    val extension: String?,
    @SerialName("path")
    val path: String?
)