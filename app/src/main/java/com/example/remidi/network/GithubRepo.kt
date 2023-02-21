package com.example.remidi.network

import com.example.remidi.commons.Result
import com.example.remidi.data.DataSearchByRepo
import com.example.remidi.data.DataUserSearchByName

class GithubRepo (private val githubRepoManager: GithubRepoManager) {
    suspend fun getListUser(userName: String): Result<DataUserSearchByName, Exception> {
        return try {
            val response = githubRepoManager.getListUser(userName)
            Result.build { response }
        } catch (e: Exception) {
            Result.build { throw e }
        }
    }

    suspend fun getUserRepo(user: String): Result<DataSearchByRepo, Exception> {
        return try {
            val response = githubRepoManager.getUserRepo(user)
            Result.build { response }
        } catch (e: Exception) {
            Result.build { throw e }
        }
    }
}