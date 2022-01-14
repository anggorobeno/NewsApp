package com.example.newsapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.newsapp.data.remote.NewsPagingSource
import com.example.newsapp.data.remote.PAGE_SIZE
import com.example.newsapp.data.remote.network.ApiService
import com.example.newsapp.domain.IRepository
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: ApiService
) : IRepository {
    override fun getNewsList() = Pager(
        PagingConfig(
            pageSize = PAGE_SIZE,
            maxSize = 100,
            enablePlaceholders = false,
        ),
        pagingSourceFactory = { NewsPagingSource(apiService) }
    ).flow
}