package com.example.newsapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.core.data.remote.response.NewsResult
import com.example.newsapp.databinding.ItemListNewsBinding
import com.example.newsapp.domain.model.NewsModel
import javax.inject.Inject

class NewsAdapter @Inject constructor() :
    PagingDataAdapter<NewsModel, NewsAdapter.ViewHolder>(NEWS_DIFF) {
    inner class ViewHolder(private val binding: ItemListNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: NewsModel) {
            with(binding) {
                tvNewsDate.text = news.publishedDate
                tvNewsTitle.text = news.title
                news.gallery.forEach { image ->
                    Glide.with(itemView)
                        .load(image.pathMedium)
                        .centerCrop()
                        .placeholder(R.drawable.image_placeholder)
                        .error(R.drawable.ic_failed)
                        .into(ivNewsImage)
                }
                binding.root.setOnClickListener {
                    onItemClickCallback.onItemClicked(news)
                }
            }
        }

    }

    lateinit var onItemClickCallback: OnItemClickCallback
    fun setOnItemCallback(callback: OnItemClickCallback) {
        this.onItemClickCallback = callback

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
        private val NEWS_DIFF = object : DiffUtil.ItemCallback<NewsModel>() {
            override fun areItemsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
                return oldItem == newItem
            }

        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: NewsModel)
    }
}