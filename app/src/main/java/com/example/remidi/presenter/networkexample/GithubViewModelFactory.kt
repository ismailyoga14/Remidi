package com.example.remidi.presenter.networkexample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.remidi.network.GithubRepo
import com.example.remidi.network.GithubRepoManager

object GithubViewModelFactory : ViewModelProvider.Factory {
    private val githubRepoManager = GithubRepoManager()
    private val githubRepo = GithubRepo(githubRepoManager)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GithubViewModel(githubRepo) as T
    }
}