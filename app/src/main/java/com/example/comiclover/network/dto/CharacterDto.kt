package com.example.comiclover.network.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDto(
    @SerialName("abilities")
    val abilities: AbilitiesDto? = null,
    @SerialName("alterEgo")
    val alterEgo: String? = null,
    @SerialName("biography")
    val biography: String? = null,
    @SerialName("caracteristics")
    val characteristics: CaracteristicsDto? = null,
    @SerialName("imagePath")
    val imagePath: String? = null,
    @SerialName("movies")
    val movies: List<String>? = null,
    @SerialName("name")
    val name: String? = null
)