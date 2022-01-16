package com.example.newsapp.ui.detail

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.core.utils.Constant
import com.example.newsapp.core.utils.Helper
import com.example.newsapp.core.utils.UiState
import com.example.newsapp.databinding.FragmentDetailNewsBinding
import com.example.newsapp.domain.model.DetailNewsModel
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
        binding.icBack.setOnClickListener {
            findNavController().navigateUp()
        }
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
                    binding.tvTitle.text = data.title
                    binding.tvWriter.text = data.writer.name
                    val dateFormatted = data.publishedDate?.let { Helper.dateFormatter(it) }
                    binding.tvDate.text =
                        getString(R.string.published_date, data.location, dateFormatted)
                    val htmlAsSpanned = Html.fromHtml(getString(R.string.html, data.content))
                    binding.tvNewsContent.text = htmlAsSpanned
                    data.gallery.forEach { image ->
                        binding.tvImageDesc.text = image.title
                        Glide.with(requireContext())
                            .load(image.pathLarge)
                            .error(R.drawable.ic_failed)
                            .into(binding.ivArticle)
                    }
                }

            }
            is UiState.Loading -> {
                binding.progressBar.isVisible = true
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}