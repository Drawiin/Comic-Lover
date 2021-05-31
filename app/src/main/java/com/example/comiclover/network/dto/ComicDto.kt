package com.example.comiclover.network.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ComicDto(
    @SerialName("description")
    val description: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("pageCount")
    val pageCount: Int?,
    @SerialName("resourceURI")
    val resourceURI: String?,
    @SerialName("thumbnail")
    val thumbnailDto: ThumbnailDto?,
    @SerialName("title")
    val title: String?
)