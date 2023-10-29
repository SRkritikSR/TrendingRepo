package com.example.trendingrepo.fake

import com.example.marsphotos.data.NetworkTrendingRepoRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class NetworkTrendingRepoRepositoryTest {
    @Test
    // the run test runs the suspend function( these are functions that are to be called from coroutinges or another suspend function
    // run test runs these suspend function from a test scope which is in turn a corouting scope
    fun networkTrendingRepoRepository_getTrendingRepos_verifyTrendingRepoList()=
        runTest {
            val repository = NetworkTrendingRepoRepository(
                repoApiServices = FakeTrendingRepoApiService()
            )
            assertEquals(FakeDataSource.trendingReposList, repository.getTrendingRepos())
        }
}