package com.example.remidi.network

import com.example.remidi.data.DataSearchByRepo
import com.example.remidi.data.DataUserSearchByName
import retrofit2.Call

class GithubRepoManager {
    private val apiClient = ApiClient.instance

    suspend fun getListUser(userName: String): DataUserSearchByName =
        apiClient.getListUser(userName)

    suspend fun getUserRepo(user: String): DataSearchByRepo =
        apiClient.getUserRepo(user)
}