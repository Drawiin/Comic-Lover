package com.example.comiclover.network


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Todo(
    @SerialName("completed")
    val completed: Boolean?,
    @SerialName("id")
    val id: Int?,
    @SerialName("title")
    val title: String?,
    @SerialName("userId")
    val userId: Int?
)