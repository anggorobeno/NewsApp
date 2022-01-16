package com.example.newsapp.domain.usecase

import androidx.paging.PagingData
import com.example.newsapp.core.utils.UiState
import com.example.newsapp.domain.model.DetailNewsModel
import com.example.newsapp.domain.model.NewsModel
import kotlinx.coroutines.flow.Flow

interface UseCase {
    fun getNewsList(): Flow<PagingData<NewsModel>>
    suspend fun getNewsDetail(path: String): Flow<UiState<List<DetailNewsModel>>>
}