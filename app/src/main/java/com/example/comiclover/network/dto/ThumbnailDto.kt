package com.example.comiclover.network.dto


import com.example.comiclover.domain.model.Thumbnail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ThumbnailDto(
    @SerialName("extension")
    val extension: String?,
    @SerialName("path")
    val path: String?
)

fun ThumbnailDto.toDomain() = Thumbnail(
    extension = extension,
    path = path
)