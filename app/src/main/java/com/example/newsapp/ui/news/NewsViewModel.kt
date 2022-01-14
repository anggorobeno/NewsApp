package com.example.newsapp.ui.news

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.newsapp.data.Repository
import com.example.newsapp.data.remote.network.ApiService
import com.example.newsapp.data.remote.response.NewsResult
import com.example.newsapp.domain.IRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: IRepository) : ViewModel() {
    fun getNewsList(): Flow<PagingData<NewsResult>> {
        return repository.getNewsList().cachedIn(viewModelScope)
    }


}