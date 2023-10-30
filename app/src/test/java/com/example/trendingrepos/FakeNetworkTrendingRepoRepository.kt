package com.example.trendingrepo.fake

import com.example.trendingrepos.data.TrendingRepoRepository
import com.example.trendingrepos.model.TrendingRepos

class FakeNetworkTrendingRepoRepository: TrendingRepoRepository {
    override suspend fun getTrendingRepos(): TrendingRepos {
        return FakeDataSource.trendingReposList
    }
}