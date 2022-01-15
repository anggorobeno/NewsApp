package com.example.newsapp.core.data.remote.response

import com.example.newsapp.domain.model.NewsModel
import com.google.gson.annotations.SerializedName

data class NewsResponse(

    @field:SerializedName("data")
    val data: List<NewsModel>,

    @field:SerializedName("response")
    val response: Response? = null
)

data class NewsResult(

    @field:SerializedName("summary")
    val summary: String? = null,

    @field:SerializedName("is_longform")
    val isLongform: Boolean? = null,

    @field:SerializedName("line")
    val line: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("tags")
    val tags: List<TagsItem?>? = null,

    @field:SerializedName("path")
    val path: String,

    @field:SerializedName("is_premium")
    val isPremium: Boolean? = null,

    @field:SerializedName("channels")
    val channels: Channels? = null,

    @field:SerializedName("publisher")
    val publisher: Publisher? = null,

    @field:SerializedName("location")
    val location: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("source_id")
    val sourceId: Int? = null,

    @field:SerializedName("writer")
    val writer: Writer,

    @field:SerializedName("published_date")
    val publishedDate: String? = null,

    @field:SerializedName("gallery")
    val gallery: List<GalleryItem>,

    @field:SerializedName("content")
    val content: String
)




