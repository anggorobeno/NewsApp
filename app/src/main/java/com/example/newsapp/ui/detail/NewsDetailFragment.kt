package com.example.newsapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentDetailNewsBinding
import com.example.newsapp.domain.model.DetailNewsModel
import com.example.newsapp.core.utils.Constant
import com.example.newsapp.core.utils.UiState
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class NewsDetailFragment : Fragment() {
    private var _binding: FragmentDetailNewsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NewsDetailViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailNewsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val path = requireArguments().getString(Constant.EXTRA_PATH)!!
        showNewsDetail(path)
    }

    private fun showNewsDetail(path: String) {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.getDetail(path).collectLatest { news ->
                handleState(news)

            }
        }
    }

    private fun handleState(state: UiState<List<DetailNewsModel>>) {
        when (state) {
            is UiState.Error -> {
                binding.progressBar.isVisible = false
                state.message.let {
                    Snackbar.make(
                        binding.root,
                        it, Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
            is UiState.Success -> {
                binding.progressBar.isVisible = false
                state.data.forEach { data ->
                    binding.tvUserName.text = data.title
                    binding.tvUserFollowings.text = data.writer.name
                    binding.tvUserFollowers.text = data.publishedDate
                    binding.tvNewsContent.loadDataWithBaseURL(
                        null,
                        data.content,
                        "text/html",
                        "utf-8",
                        null
                    )
                    data.gallery.forEach { image ->
                        Glide.with(requireContext())
                            .load(image.pathLarge)
                            .error(R.drawable.ic_failed)
                            .into(binding.ivAvatar)
                    }
                }

            }
            is UiState.Loading -> {
                binding.progressBar.isVisible = true
            }
        }

    }

}