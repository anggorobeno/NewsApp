package com.example.newsapp.domain.repository

import androidx.paging.PagingData
import com.example.newsapp.core.data.remote.response.NewsResult
import com.example.newsapp.domain.model.DetailNewsModel
import com.example.newsapp.core.utils.UiState
import com.example.newsapp.domain.model.NewsModel
import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun getNewsList(): Flow<PagingData<NewsModel>>
    suspend fun getNewsDetail(path : String): Flow<UiState<List<DetailNewsModel>>>
}