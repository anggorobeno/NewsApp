package com.example.newsapp.core.utils

import com.example.newsapp.domain.model.DetailNewsModel
import com.example.newsapp.core.data.remote.response.DetailNewsResponse
import com.example.newsapp.core.data.remote.response.NewsResponse
import com.example.newsapp.core.data.remote.response.NewsResult
import com.example.newsapp.domain.model.NewsModel

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

    fun mapNewsResponseToModel(input: List<NewsResult>): List<NewsModel> {
        return input.map {
            with(it) {
                NewsModel(
                    summary,
                    isLongform,
                    line,
                    title, tags, path,
                    isPremium, channels, publisher,
                    location, id, sourceId,
                    writer, publishedDate, gallery, content
                )
            }
        }

    }
//        val listNews = ArrayList<NewsModel>()
//        for (data in input) {
//            val news = NewsModel(
//                data.summary,
//                data.isLongform,
//                data.line,
//                data.title, data.tags, data.path,
//                data.isPremium, data.channels, data.publisher,
//                data.location, data.id, data.sourceId, data.writer,
//                data.publishedDate,
//                data.gallery, data.content
//            )
//            listNews.add(news)
//        }
//        return listNews
//    }
}