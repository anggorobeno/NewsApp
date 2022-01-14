package com.example.newsapp.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.data.remote.response.NewsResult
import com.example.newsapp.databinding.ItemListNewsBinding
import javax.inject.Inject

class NewsAdapter @Inject constructor() :
    PagingDataAdapter<NewsResult, NewsAdapter.ViewHolder>(NEWS_DIFF) {
    inner class ViewHolder(private val binding: ItemListNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: NewsResult) {
            with(binding) {
                tvNewsDate.text = news.publishedDate
                tvNewsTitle.text = news.title
                for (image in news.gallery) {
                    Log.d("TAG", "bind: ${image.pathMedium} ")
                    Glide.with(itemView)
                        .load(image.pathMedium)
                        .centerCrop()
                        .placeholder(R.drawable.image_placeholder)
                        .error(R.drawable.ic_failed)
                        .into(ivNewsImage)
                }


            }
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = getItem(position)
        if (news != null) {
            holder.bind(news)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemListNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    companion object {
        private val NEWS_DIFF = object : DiffUtil.ItemCallback<NewsResult>() {
            override fun areItemsTheSame(oldItem: NewsResult, newItem: NewsResult): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: NewsResult, newItem: NewsResult): Boolean {
                return oldItem == newItem
            }

        }
    }
}