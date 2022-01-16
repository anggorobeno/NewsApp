package com.example.newsapp.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.newsapp.domain.model.NewsModel
import com.example.newsapp.domain.usecase.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {
    fun getNewsList(): Flow<PagingData<NewsModel>> {
        return useCase.getNewsList().cachedIn(viewModelScope)
    }
}