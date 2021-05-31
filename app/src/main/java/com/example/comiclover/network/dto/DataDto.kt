package com.example.comiclover.network.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataDto(
    @SerialName("count")
    val count: Int?,
    @SerialName("limit")
    val limit: Int?,
    @SerialName("offset")
    val offset: Int?,
    @SerialName("results")
    val results: List<ComicDto>?,
    @SerialName("total")
    val total: Int?
)