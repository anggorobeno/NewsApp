package com.example.newsapp.core.data.remote.response

import com.example.newsapp.domain.model.NewsModel
import com.google.gson.annotations.SerializedName

data class NewsResponse(

    @field:SerializedName("data")
    val data: List<NewsModel>,

    @field:SerializedName("response")
    val response: Response? = null
)






