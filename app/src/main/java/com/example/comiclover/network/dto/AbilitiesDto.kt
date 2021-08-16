package com.example.comiclover.network.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AbilitiesDto(
    @SerialName("agility")
    val agility: Int,
    @SerialName("endurance")
    val endurance: Int,
    @SerialName("force")
    val force: Int,
    @SerialName("intelligence")
    val intelligence: Int,
    @SerialName("velocity")
    val velocity: Int
)