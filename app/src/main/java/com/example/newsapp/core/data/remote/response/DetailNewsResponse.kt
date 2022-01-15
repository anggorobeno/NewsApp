package com.example.newsapp.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class DetailNewsResponse(

    @field:SerializedName("data")
    val data: List<NewsResult> ,

    @field:SerializedName("response")
    val response: Response? = null,

    @field:SerializedName("is_user_premium")
    val isUserPremium: Boolean? = null
)

data class GalleryItem(

    @field:SerializedName("path_origin")
    val pathOrigin: String? = null,

    @field:SerializedName("path_medium")
    val pathMedium: String? = null,

    @field:SerializedName("path_thumbnail")
    val pathThumbnail: String? = null,

    @field:SerializedName("photographer")
    val photographer: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("source")
    val source: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("keyword")
    val keyword: String? = null,

    @field:SerializedName("path_large")
    val pathLarge: String? = null,

    @field:SerializedName("path_small")
    val pathSmall: String? = null,

    @field:SerializedName("content")
    val content: Any? = null
)

data class Response(

    @field:SerializedName("code")
    val code: Int? = null,

    @field:SerializedName("text")
    val text: String? = null
)

data class Channels(

    @field:SerializedName("parent")
    val parent: String? = null,

    @field:SerializedName("color")
    val color: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null
)


data class Publisher(

    @field:SerializedName("name")
    val name: Any? = null,

    @field:SerializedName("id")
    val id: Any? = null,

    @field:SerializedName("pic")
    val pic: String? = null,

    @field:SerializedName("job")
    val job: Any? = null
)

data class TagsItem(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("link")
    val link: String? = null,

    @field:SerializedName("id")
    val id: Int? = null
)

data class Writer(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("pic")
    val pic: String? = null,

    @field:SerializedName("job")
    val job: String? = null
)
