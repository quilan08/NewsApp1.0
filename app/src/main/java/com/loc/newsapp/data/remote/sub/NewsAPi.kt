package com.loc.newsapp.data.remote.sub

import androidx.room.PrimaryKey
import com.loc.newsapp.data.remote.dto.NewsResponse
import com.loc.newsapp.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi  {
    @GET("everything")
    suspend fun getNews(
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ): NewsResponse

    @GET("everything")
    suspend fun searchNews(
        @Query("q") searchQuery : String,
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ): NewsResponse
}