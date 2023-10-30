package com.example.trendingrepos.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.trendingrepos.TrendingRepoApplication
import com.example.trendingrepos.data.TrendingRepoRepository
import com.example.trendingrepos.model.TrendingRepos
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface RepoUiState {
    data class Success(val TrendingRepos: TrendingRepos) : RepoUiState
    object Error : RepoUiState
    object Loading : RepoUiState
}
private const val TAG="RepoViewModel"
class RepoViewModel(private val trendingRepoRepository: TrendingRepoRepository) : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var repoUiState: RepoUiState by mutableStateOf(RepoUiState.Loading)
        private set

    /**
     * Call getTrendingRepos() on init so we can display status immediately.
     */
    init {
        getTrendingRepos()
    }


    private fun getTrendingRepos() {
        viewModelScope.launch {
            Log.i(TAG, "getTrendingRepos: I am inside view model")
            repoUiState = RepoUiState.Loading
            repoUiState = try {
                Log.i(TAG, "getTrendingRepos: before data fetch")
//                val listResults=trendingRepoRepository.getTrendingRepos()
                Log.i(TAG, "getTrendingRepos: after data fetch")
                RepoUiState.Success(trendingRepoRepository.getTrendingRepos())
            } catch (e: IOException) {
                Log.i(TAG, "getTrendingRepos: ioexception")
                RepoUiState.Error
            } catch (e: HttpException) {
                Log.i(TAG, "getTrendingRepos: httpException")
                RepoUiState.Error
            }
        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as TrendingRepoApplication)
                val trendingRepoRepository = application.container.trendingRepoRepository
                Log.i(TAG, "the repository is ${trendingRepoRepository}: ")
                RepoViewModel(trendingRepoRepository = trendingRepoRepository)
            }
        }
    }
}