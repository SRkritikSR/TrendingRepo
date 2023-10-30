package com.example.trendingrepos.network

import com.example.trendingrepos.model.TrendingRepos
import retrofit2.http.GET
import retrofit2.http.Query

// Scalars convertors has the job to convert the data from json to string and other formats

interface  RepoApiServices{

    // this appends the photos at the end of the base url, when the get Photos function is called
    @GET("/search/repositories?sort=stars")

    suspend fun getRepos(
        @Query("q") query: String,
        @Query("sort") sort: String,
        @Query("order") order: String,
        @Query("per_page") per_page: Int
    ): TrendingRepos
}

