package com.example.newsapp.domain.model

import com.example.newsapp.core.data.remote.response.*

data class NewsModel(
    val summary: String? = null,

    val isLongform: Boolean? = null,

    val line: String? = null,

    val title: String? = null,

    val tags: List<TagsItem?>? = null,

    val path: String,

    val isPremium: Boolean? = null,

    val channels: Channels? = null,

    val publisher: Publisher? = null,

    val location: String? = null,

    val id: String? = null,

    val sourceId: Int? = null,

    val writer: Writer,

    val publishedDate: String? = null,

    val gallery: List<GalleryItem>,

    val content: String
)
