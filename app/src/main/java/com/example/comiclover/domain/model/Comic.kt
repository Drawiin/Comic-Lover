package com.example.comiclover.domain.model

data class Comic(
    val description: String?,
    val id: Int?,
    val pageCount: Int?,
    val resourceURI: String?,
    val thumbnail: Thumbnail?,
    val title: String?
)