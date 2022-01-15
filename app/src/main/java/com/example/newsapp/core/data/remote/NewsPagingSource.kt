package com.example.newsapp.core.data.remote

import android.util.Log
import androidx.paging.PagingSource
import com.example.newsapp.core.data.remote.network.ApiService
import com.example.newsapp.core.data.remote.response.NewsResult
import com.example.newsapp.core.utils.DataMapper
import com.example.newsapp.domain.model.NewsModel
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


private const val STARTING_PAGE_INDEX: Int = 1
const val PAGE_SIZE: Int = 30


class NewsPagingSource @Inject constructor(private val apiService: ApiService) :
    PagingSource<Int, NewsModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsModel> {
        return try {
            val position = params.key ?: STARTING_PAGE_INDEX
            val offset = PAGE_SIZE * position - PAGE_SIZE
            val response = apiService.getNewsList(params.loadSize, offset)
            val result = response.body()
            val data = result!!.data
            if (response.isSuccessful) {
                LoadResult.Page(
                    data = data,
                    prevKey = if (position == 1) null else position - 1,
                    nextKey = if (data.isEmpty()) null else position + 1
                )
            } else {
                LoadResult.Error(Throwable(message = response.message()))
            }

        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: IOException) {
            LoadResult.Error(e)
        }
    }
}