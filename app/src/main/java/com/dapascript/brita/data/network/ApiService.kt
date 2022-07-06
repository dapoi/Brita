package com.dapascript.brita.data.network

import com.dapascript.brita.data.model.NewsResponse
import com.dapascript.brita.data.model.SearchNewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @Author: Luthfi Daffa Prabowo
 * @Date: 06/07/2022 00.05
 */
interface ApiService {

    @GET("top-headlines")
    suspend fun getTechNews(
        @Query("pageSize") pageSize: Int = 30,
        @Query("page") page: Int = 1,
        @Query("apiKey") apiKey: String = "b5e957cc160649c09671f3dc74c2f3b2",
        @Query("category") category: String = "technology",
    ): NewsResponse

    @GET("top-headlines")
    suspend fun getBusinessNews(
        @Query("pageSize") pageSize: Int = 30,
        @Query("page") page: Int = 1,
        @Query("apiKey") apiKey: String = "b5e957cc160649c09671f3dc74c2f3b2",
        @Query("category") category: String = "business",
    ): NewsResponse

    @GET("everything")
    suspend fun getNewsSearch(
        @Query("q") query: String,
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 30,
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("apiKey") apiKey: String = "b5e957cc160649c09671f3dc74c2f3b2"
    ): SearchNewsResponse
}