package com.example.trendingrepos.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Owner (
    @SerialName("id")
    val id: Int,
    @SerialName("avatar_url")
    val avatarUrl: String
)
