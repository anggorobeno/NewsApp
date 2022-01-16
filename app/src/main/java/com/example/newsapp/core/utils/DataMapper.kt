package com.example.newsapp.core.utils

import com.example.newsapp.core.data.remote.response.DetailNewsResponse
import com.example.newsapp.domain.model.DetailNewsModel

object DataMapper {
    fun mapNewsDetailResponseToModel(input: DetailNewsResponse): List<DetailNewsModel> {
        val listNews = ArrayList<DetailNewsModel>()
        for (data in input.data) {
            val news = DetailNewsModel(
                data.summary,
                data.isLongform,
                data.line,
                data.title, data.tags, data.path,
                data.isPremium, data.channels, data.publisher,
                data.location, data.id, data.sourceId, data.writer,
                data.publishedDate,
                data.gallery, data.content
            )
            listNews.add(news)
        }
        return listNews
    }
}