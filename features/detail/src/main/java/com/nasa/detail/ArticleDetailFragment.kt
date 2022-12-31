package com.nasa.detail

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.google.gson.Gson

import com.nasa.presentation.base.BaseFragment
import com.photos.detail.R
import com.photos.detail.databinding.FragmentArticleDetailBinding


class ArticleDetailFragment
    : BaseFragment<ArticleDetailViewModel , FragmentArticleDetailBinding>(R.layout.fragment_article_detail) {
    override val binding: FragmentArticleDetailBinding
        by viewBinding(FragmentArticleDetailBinding::bind)
    override val fragmentViewModel: ArticleDetailViewModel
        by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  fragmentViewModel.article = Gson().fromJson(arguments?.getString("article")?.trim() , Article::class.java)
    }
    override fun onCreated() {
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = fragmentViewModel
        }

      //  requireActivity().title = fragmentViewModel.article?.title
    }


}