package com.example.newsapp.domain

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newsapp.data.remote.response.NewsResult
import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun getNewsList(): Flow<PagingData<NewsResult>>
}