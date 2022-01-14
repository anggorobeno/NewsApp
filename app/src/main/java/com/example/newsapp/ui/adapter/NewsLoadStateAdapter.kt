package com.example.newsapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.NewsLoadStateFooterBinding

class NewsLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<NewsLoadStateAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        val binding =
            NewsLoadStateFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: NewsLoadStateFooterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.btnRetry.setOnClickListener {
                retry.invoke()
            }

        }

        fun bind(loadState: LoadState) {
            with(binding) {
                if (loadState is LoadState.Error) {
                    binding.tvError.text = loadState.error.localizedMessage
                }

                progressCircular.isVisible = loadState is LoadState.Loading
                btnRetry.isVisible = loadState !is LoadState.Loading
                tvError.isVisible = loadState !is LoadState.Loading
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }
}