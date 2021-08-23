package com.example.comiclover.features.main.data.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class AllCharactersDto(
    @SerialName("aliens")
    val aliens: List<CharacterDto>,
    @SerialName("antiHeroes")
    val antiHeroes: List<CharacterDto>,
    @SerialName("heroes")
    val heroes: List<CharacterDto>,
    @SerialName("humans")
    val humans: List<CharacterDto>,
    @SerialName("villains")
    val villains: List<CharacterDto>
): Parcelable

@Parcelize
@Serializable
data class CharacterDto(
    @SerialName("abilities")
    val abilities: AbilitiesDto? = null,
    @SerialName("alterEgo")
    val alterEgo: String? = null,
    @SerialName("biography")
    val biography: String? = null,
    @SerialName("caracteristics")
    val characteristics: CharacteristicsDto? = null,
    @SerialName("imagePath")
    val imagePath: String? = null,
    @SerialName("movies")
    val movies: List<String>? = null,
    @SerialName("name")
    val name: String? = null
): Parcelable

@Parcelize
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
): Parcelable

@Parcelize
@Serializable
data class CharacteristicsDto(
    @SerialName("birth")
    val birth: String,
    @SerialName("height")
    val height: HeightDto,
    @SerialName("universe")
    val universe: String,
    @SerialName("weight")
    val weight: WeightDto
): Parcelable

@Parcelize
@Serializable
data class HeightDto(
    @SerialName("unity")
    val unity: String,
    @SerialName("value")
    val value: Double
): Parcelable

@Parcelize
@Serializable
data class WeightDto(
    @SerialName("unity")
    val unity: String,
    @SerialName("value")
    val value: Int
): Parcelable