package com.nasa.detail

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.google.gson.Gson
import com.nasa.domain.models.Photo

import com.nasa.presentation.base.BaseFragment
import com.photos.detail.R
import com.photos.detail.databinding.FragmentDetailBinding


class DetailFragment
    : BaseFragment<DetailViewModel, FragmentDetailBinding>(R.layout.fragment_detail) {
    override val binding: FragmentDetailBinding
            by viewBinding(FragmentDetailBinding::bind)
    override val fragmentViewModel: DetailViewModel
            by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentViewModel.photo = Gson().fromJson(
            arguments?.getString("photo"), Photo::class.java
        )
    }

    override fun onCreated() {
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = fragmentViewModel
        }
        requireActivity().title = fragmentViewModel.photo?.rover?.name
    }


}