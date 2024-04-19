package com.example.data.data_sources.remote.news

import com.example.data.models.NewsResponseModel
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsService {
    @GET("everything")
    suspend fun getAllNews(
        @Query("q") search : String?,
        @Query("apiKey") apiKeyAuthentication: String,
        @Query("pageSize") pageSize : Int,
        @Query("page") page : Int
        ) : NewsResponseModel
}