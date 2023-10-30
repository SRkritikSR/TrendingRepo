package com.example.trendingrepos.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TrendingRepos (
    @SerialName("total_count")
    val total: Int = 0,
    @SerialName("items")
    val items:List<Repos>,

    )
