package com.example.newsapp.ui.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.ui.adapter.NewsAdapter
import com.example.newsapp.ui.adapter.NewsLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class NewsFragment : Fragment() {
    @Inject
    lateinit var newsAdapter: NewsAdapter
    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NewsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getNewsList()
        binding.swipeRefreshLayout.setOnRefreshListener {
            Toast.makeText(requireContext(), "Refreshed", Toast.LENGTH_LONG).show()
            newsAdapter.refresh()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun getNewsList() {
        lifecycleScope.launchWhenCreated {
            viewModel.getNewsList().collectLatest { newsList ->
                newsAdapter.submitData(viewLifecycleOwner.lifecycle, newsList)
                showRv()
            }
        }

    }

    private fun showRv() {
        with(binding.rvUser) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = newsAdapter.withLoadStateHeaderAndFooter(
                header = NewsLoadStateAdapter { newsAdapter.retry() },
                footer = NewsLoadStateAdapter { newsAdapter.retry() }
            )
            newsAdapter.addLoadStateListener { loadState ->
                with(binding) {
                    progressCircular.isVisible = loadState.source.refresh is LoadState.Loading
                    rvUser.isVisible = loadState.source.refresh is LoadState.NotLoading
                    tvEmptyStateDesc.isVisible =
                        loadState.source.refresh is LoadState.Error
                    ivEmptyState.isVisible = loadState.source.refresh is LoadState.Error
                    btnRetry.isVisible = loadState.source.refresh is LoadState.Error
                    swipeRefreshLayout.isEnabled = loadState.source.refresh is LoadState.NotLoading
                    if (loadState.source.refresh is LoadState.Error) {
                        ivEmptyState.setImageResource(R.drawable.ic_failed)
                        ivEmptyState.isVisible = true
                        rvUser.isVisible = false
                        tvEmptyStateDesc.text = getString(R.string.something_went_wrong)
                        tvEmptyStateDesc.isVisible = true
                        btnRetry.isVisible = true
                    }

                }
            }

            binding.btnRetry.setOnClickListener {
                newsAdapter.retry()
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}