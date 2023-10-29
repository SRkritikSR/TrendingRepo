package com.example.marsphotos.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Owner (
    val id: Int,
    @SerialName("avatar_url")
    val avatarUrl: String
)
