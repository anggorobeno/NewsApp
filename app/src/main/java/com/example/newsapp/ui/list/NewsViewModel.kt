package com.example.newsapp.ui.list

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.newsapp.core.data.remote.response.NewsResult
import com.example.newsapp.domain.model.NewsModel
import com.example.newsapp.domain.repository.IRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: IRepository
) : ViewModel() {
    fun getNewsList(): Flow<PagingData<NewsModel>> {
        return repository.getNewsList().cachedIn(viewModelScope)
    }
}