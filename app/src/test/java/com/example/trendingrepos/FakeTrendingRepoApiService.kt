package com.example.trendingrepos

import com.example.trendingrepo.fake.FakeDataSource
import com.example.trendingrepos.model.TrendingRepos
import com.example.trendingrepos.network.RepoApiServices

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