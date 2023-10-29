package com.example.trendingrepo.fake

import com.example.marsphotos.data.TrendingRepoRepository
import com.example.marsphotos.model.TrendingRepos

class FakeNetworkTrendingRepoRepository: TrendingRepoRepository{
    override suspend fun getTrendingRepos(): TrendingRepos {
        return FakeDataSource.trendingReposList
    }
}