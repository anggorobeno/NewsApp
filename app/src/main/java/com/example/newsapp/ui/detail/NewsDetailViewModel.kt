package com.example.newsapp.ui.detail

import androidx.lifecycle.ViewModel
import com.example.newsapp.domain.repository.IRepository
import com.example.newsapp.domain.model.DetailNewsModel
import com.example.newsapp.core.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor(private val repository: IRepository) : ViewModel() {
    suspend fun getDetail(path: String): Flow<UiState<List<DetailNewsModel>>> {
        return repository.getNewsDetail(path)

    }
}