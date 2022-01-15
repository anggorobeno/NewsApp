package com.example.newsapp.core.data.remote.network

import com.example.newsapp.core.data.remote.response.DetailNewsResponse
import com.example.newsapp.core.data.remote.response.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService  {

    @GET("articles/seasia")
    suspend fun getNewsList(
        @Query("limit") limit : Int,
        @Query("skip") offset : Int
    ) : Response<NewsResponse>

    @GET("article/url")
    suspend fun getNewsDetail(
        @Query("path") path : String
    ) : Response<DetailNewsResponse>
}