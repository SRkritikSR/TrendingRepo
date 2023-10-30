    package com.example.trendingrepos.data

    import com.example.trendingrepos.model.TrendingRepos
    import com.example.trendingrepos.network.RepoApiServices

    interface TrendingRepoRepository {
        suspend fun getTrendingRepos() : TrendingRepos
    }
    class NetworkTrendingRepoRepository(
        private val repoApiServices: RepoApiServices
    ):TrendingRepoRepository {
        override suspend fun getTrendingRepos(): TrendingRepos=repoApiServices.getRepos(
            query = "created:>2023-01-01",
            sort="stars",
            order="desc",
            per_page = 2,
        )
    }