package com.example.trendingrepos.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Repos (
    @SerialName("id")
    val id: Long,
    @SerialName("full_name")
    val fullName:String,
    @SerialName("description")
    val description: String?,
    @SerialName("owner")
    val owner: Owner,

    )

