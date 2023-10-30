package com.example.trendingrepo.fake

import android.util.Log

import com.example.trendingrepo.fake.rules.TestDispatcherRule
import com.example.trendingrepos.ui.screens.RepoUiState
import com.example.trendingrepos.ui.screens.RepoViewModel
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
                RepoUiState.Success(FakeNetworkTrendingRepoRepository().getTrendingRepos()),
                repoViewModel.repoUiState)
        }
}