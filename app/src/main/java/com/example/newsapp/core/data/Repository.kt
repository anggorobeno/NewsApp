package com.example.newsapp.core.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.newsapp.core.data.remote.NewsPagingSource
import com.example.newsapp.core.data.remote.PAGE_SIZE
import com.example.newsapp.core.data.remote.network.ApiService
import com.example.newsapp.domain.repository.IRepository
import com.example.newsapp.domain.model.DetailNewsModel
import com.example.newsapp.core.utils.DataMapper
import com.example.newsapp.core.utils.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
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

    override suspend fun getNewsDetail(path: String): Flow<UiState<List<DetailNewsModel>>> {
        return flow<UiState<List<DetailNewsModel>>> {
            try {
                emit(UiState.Loading)
                val response = apiService.getNewsDetail(path)
                val result = response.body()
                if (response.isSuccessful && result != null) {
                    val mapper = DataMapper.mapNewsDetailResponseToModel(result)
                    emit(UiState.Success(mapper))
                } else {
                    emit(UiState.Error(response.code().toString()))
                }
            } catch (e: HttpException) {
                emit(UiState.Error(e.message()))

            } catch (e: IOException) {
                emit(UiState.Error(e.message!!))
            }
        }
    }


}

