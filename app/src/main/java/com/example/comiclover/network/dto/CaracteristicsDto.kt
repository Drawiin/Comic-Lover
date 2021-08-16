package com.example.comiclover.network.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CaracteristicsDto(
    @SerialName("birth")
    val birth: String,
    @SerialName("height")
    val height: HeightDto,
    @SerialName("universe")
    val universe: String,
    @SerialName("weight")
    val weight: WeightDto
)