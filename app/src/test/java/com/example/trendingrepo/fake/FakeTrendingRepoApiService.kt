package com.example.trendingrepo.fake

import com.example.marsphotos.model.TrendingRepos
import com.example.marsphotos.network.RepoApiServices

class FakeTrendingRepoApiService: RepoApiServices {
    override suspend fun getRepos(
        query: String,
        sort: String,
        order: String,
        per_page: Int
    ): TrendingRepos {
        return FakeDataSource.trendingReposList
    }
}