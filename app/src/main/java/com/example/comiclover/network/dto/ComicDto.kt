package com.example.comiclover.network.dto


import com.example.comiclover.domain.model.Comic
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

fun ComicDto.toDomain(): Comic = Comic(
    description = description,
    id = id,
    pageCount = pageCount,
    resourceURI = resourceURI,
    thumbnail = thumbnailDto?.toDomain(),
    title = title
)