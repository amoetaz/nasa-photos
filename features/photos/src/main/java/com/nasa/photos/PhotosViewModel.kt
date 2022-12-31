package com.nasa.photos

import androidx.lifecycle.viewModelScope
import com.nasa.domain.models.Photo
import com.nasa.domain.modules.photos.useCases.GetPhotosUseCase
import com.nasa.domain.utils.Resource
import com.nasa.presentation.Extra.API_KEY
import com.nasa.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(private val getArticlesUseCase: GetPhotosUseCase) :
    BaseViewModel() {

    private val _photosResponse = MutableStateFlow<Resource<List<Photo>?>>(Resource.Default)
    val photosResponse = _photosResponse

    private val _otherPhotosResponse = MutableStateFlow<Resource<List<Photo>?>>(Resource.Default)
    val otherPhotosResponse = _otherPhotosResponse

    val photosList = MutableStateFlow<ArrayList<Photo>?>(null)
    var page = 1

    var roverName = "Curiosity"
    init {
        getPhotos()
    }

    fun getPhotos() {
        page = 1
        getArticlesUseCase(roverName,page, API_KEY, 1000)
            .onEach {
                _photosResponse.value = it
                if (it.toData() != null)
                    photosList.value = it.toData() as ArrayList<Photo>
                increamnetPageNum(it.toData())
            }
            .launchIn(viewModelScope)
    }

    fun getOtherPhotos() {
        if (page != -1) {
            getArticlesUseCase(roverName,page, API_KEY, 1000)
                .onEach {
                    _otherPhotosResponse.value = it
                    if (it.toData() != null){
                        photosList.value?.addAll(it.toData() as ArrayList<Photo>)
                        photosList.value = photosList.value
                    }
                    increamnetPageNum(it.toData())
                }
                .launchIn(viewModelScope)
        }
    }

    private fun increamnetPageNum(data: List<Photo>?) {
        data?.let {

            page = if (it.isEmpty()) -1 else ++page
        }
    }

}