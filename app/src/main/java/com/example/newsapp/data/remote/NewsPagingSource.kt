package com.example.newsapp.data.remote

import android.util.Log
import androidx.paging.PagingSource
import com.example.newsapp.data.remote.network.ApiService
import com.example.newsapp.data.remote.response.NewsResult
import dagger.Component
import dagger.Subcomponent
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


private const val STARTING_PAGE_INDEX: Int = 1
const val PAGE_SIZE: Int = 30


class NewsPagingSource @Inject constructor(private val apiService: ApiService) :
    PagingSource<Int, NewsResult>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsResult> {

        return try {
            val position = params.key ?: STARTING_PAGE_INDEX
            Log.d("TAG", "load: $position")
            val offset = PAGE_SIZE * position - PAGE_SIZE
            val response = apiService.getNewsList(params.loadSize, offset)
            val responseData = mutableListOf<NewsResult>()
            val data = response.body()?.data ?: emptyList()
            responseData.addAll(data)
            LoadResult.Page(
                data = responseData,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (responseData.isEmpty()) null else position + 1
            )
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: IOException) {
            LoadResult.Error(e)

        }


    }


}