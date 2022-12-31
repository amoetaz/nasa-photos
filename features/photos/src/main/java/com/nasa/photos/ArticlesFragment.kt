package com.nasa.photos


import androidx.fragment.app.viewModels
import com.nasa.domain.utils.Resource
import com.nasa.photos.databinding.FragmentArticlesBinding


import com.nasa.presentation.base.BaseFragment
import com.nasa.presentation.extensions.onLastItemReached


import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticlesFragment : BaseFragment<PhotosViewModel , FragmentArticlesBinding>(R.layout.fragment_articles) {
    override val binding: FragmentArticlesBinding
        by viewBinding(FragmentArticlesBinding::bind)
    override val fragmentViewModel: PhotosViewModel
        by viewModels()

    private val articleAdapter by lazy { ArticlesAdapter() }
    override fun onCreated() {
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = fragmentViewModel
        }
        binding.rvList.adapter = articleAdapter
        articleAdapterClicks()
        listScrollListener()
        requireActivity().title = "Mars Rover Photos"
    }

    private fun listScrollListener() {
        binding.rvList.onLastItemReached {
            if (fragmentViewModel.otherPhotosResponse.value !is Resource.Loading){
                fragmentViewModel.getOtherPhotos()
            }
        }
    }

    private fun articleAdapterClicks() {
        articleAdapter.onItemClick = {
          /*  Log.d("sdfsdfdsfds", "articleAdapterClicks: $it")
            val request = NavDeepLinkRequest.Builder
                .fromUri("android-app://com.redditnews.articles/articlesFragment?article=${Gson().toJson(it.apply {desc = desc?.replace("#" , "") })}".toUri())
                .build()
            findNavController().navigate(request)*/

        }
    }

}

