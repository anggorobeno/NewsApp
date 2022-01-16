package com.example.newsapp.domain.usecase

import androidx.paging.PagingData
import com.example.newsapp.core.utils.UiState
import com.example.newsapp.domain.model.DetailNewsModel
import com.example.newsapp.domain.model.NewsModel
import com.example.newsapp.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Interactor @Inject constructor(private val repository: IRepository) : UseCase {
    override fun getNewsList(): Flow<PagingData<NewsModel>> {
        return repository.getNewsList()
    }

    override suspend fun getNewsDetail(path: String): Flow<UiState<List<DetailNewsModel>>> {
        return repository.getNewsDetail(path)
    }
}