package com.example.trendingrepo.fake

import android.util.Log
import com.example.marsphotos.ui.screens.RepoUiState
import com.example.marsphotos.ui.screens.RepoViewModel
import com.example.trendingrepo.fake.rules.TestDispatcherRule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class RepoViewModelTest {
    private val TAG="Testing"
    @get:Rule
    val testDispatcher=TestDispatcherRule()
    @Test
    fun repoViewModel_getTrendingRepos_verifyRepoUiStateSuccess()=
        runTest {
            val repoViewModel= RepoViewModel(
                    trendingRepoRepository = FakeNetworkTrendingRepoRepository()
            )
            assertEquals(
                RepoUiState.Success("Total photos recieved are... ${FakeNetworkTrendingRepoRepository().getTrendingRepos().items.size} "),
                repoViewModel.repoUiState)
        }
}