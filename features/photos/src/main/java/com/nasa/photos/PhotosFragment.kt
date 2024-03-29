package com.nasa.photos

import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.nasa.domain.utils.Resource
import com.nasa.photos.databinding.FragmentPhotosBinding
import com.nasa.presentation.base.BaseFragment
import com.nasa.presentation.extensions.onLastItemReached
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotosFragment :
    BaseFragment<PhotosViewModel, FragmentPhotosBinding>(R.layout.fragment_photos) {
    override val binding: FragmentPhotosBinding
            by viewBinding(FragmentPhotosBinding::bind)
    override val fragmentViewModel: PhotosViewModel
            by viewModels()

    private val articleAdapter by lazy { PhotosAdapter() }
    override fun onCreated() {
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = fragmentViewModel
        }
        binding.rvList.adapter = articleAdapter
        articleAdapterClicks()
        listScrollListener()
        requireActivity().title = "Mars Rover Photos"
        registerForContextMenu(binding.ivFilter)
        binding.ivFilter.setOnClickListener {
            requireActivity().openContextMenu(it)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu.setHeaderTitle("Choose a Rover");
        menu.add(0, v.id, 0, "Curiosity");
        menu.add(0, v.id, 0, "Opportunity");
        menu.add(0, v.id, 0, "Spirit");
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.title) {
            "Curiosity" -> {
                fragmentViewModel.roverName = "Curiosity"
            }
            "Opportunity" -> {
                fragmentViewModel.roverName = "Opportunity"
            }
            else -> {
                fragmentViewModel.roverName = "Spirit"
            }
        }
        fragmentViewModel.getPhotos()
        return true
    }

    private fun listScrollListener() {
        binding.rvList.onLastItemReached {
            if (fragmentViewModel.otherPhotosResponse.value !is Resource.Loading) {
                fragmentViewModel.getOtherPhotos()
            }
        }
    }

    private fun articleAdapterClicks() {
        articleAdapter.onItemClick = {

            val request = NavDeepLinkRequest.Builder
                .fromUri("android-app://com.nasa.photos/photosFragment?photo=${Gson().toJson(it)}".toUri())
                .build()
            findNavController().navigate(request)

        }
    }

}

