package com.example.marsphotos.ui.screens

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
import com.example.marsphotos.TrendingRepoApplication

import com.example.marsphotos.data.TrendingRepoRepository
import com.example.marsphotos.model.TrendingRepos

import com.example.marsphotos.network.MarsPhoto

import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
sealed interface RepoUiState {
    data class Success(val TrendingRepos:TrendingRepos) : RepoUiState
    object Error : RepoUiState
    object Loading : RepoUiState
}

class RepoViewModel(private val trendingRepoRepository: TrendingRepoRepository) : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var repoUiState: RepoUiState by mutableStateOf(RepoUiState.Loading)
        private set

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getTrendingRepos()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [MutableList].
     */
    private fun getTrendingRepos() {
        viewModelScope.launch {
            repoUiState = RepoUiState.Loading
            repoUiState = try {
                val listResults=trendingRepoRepository.getTrendingRepos()
                RepoUiState.Success(listResults)
            } catch (e: IOException) {
                RepoUiState.Error
            } catch (e: HttpException) {
                RepoUiState.Error
            }
        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as TrendingRepoApplication)
                val trendingRepoRepository = application.container.trendingRepoRepository
                RepoViewModel(trendingRepoRepository = trendingRepoRepository)
            }
        }
    }
}