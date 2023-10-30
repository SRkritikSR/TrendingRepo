package com.example.trendingrepos.data

import com.example.trendingrepos.network.RepoApiServices
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val trendingRepoRepository: TrendingRepoRepository
}
private const val TAG = "AppContainer"
class DefaultAppContainer : AppContainer {
    private val REPO_BASE_URL = "https://api.github.com/"

    private val json = Json { ignoreUnknownKeys = true }
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(REPO_BASE_URL)
        .build()

    // Scalars convertors has the job to convert the data from json to string and other formats
    private val retrofitService: RepoApiServices by lazy {
        retrofit.create(RepoApiServices::class.java)
    }

    // this function is sending retrofit services as args and the function get Trending repos is implemented with it's return type being retrofit services.getTrendingRepos()
    override val trendingRepoRepository: TrendingRepoRepository by lazy {
        NetworkTrendingRepoRepository(retrofitService)
    }

}