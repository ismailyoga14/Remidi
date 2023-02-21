package com.example.remidi.network

import com.example.remidi.data.DataSearchByRepo
import com.example.remidi.data.DataUserSearchByName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET(API_LIST_USER)
    fun getUserRepo(
        @Path("user") user: String
    ): DataSearchByRepo

    @GET(API_LIST_REPO)
    suspend fun getListUser(
        @Query("q") userName: String,
        @Query("per_page") perPage: Int = 5,
        @Query("page") page: Int = 1,
    ): DataUserSearchByName


    companion object {
        const val API_LIST_USER = "users/{user}/repos"
        const val API_LIST_REPO = "search/repositories"
    }
}