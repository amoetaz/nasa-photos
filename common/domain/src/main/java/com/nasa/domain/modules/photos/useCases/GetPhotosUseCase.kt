package com.nasa.domain.modules.photos.useCases

import com.nasa.domain.modules.photos.repository.PhotosRepository
import com.nasa.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(private val articlesRepository: PhotosRepository) {


    operator fun invoke(rover: String, page: Int, apiKey: String, sol: Int) = flow {

        emit(Resource.Loading)

        emit(articlesRepository.getPhotos(rover, page, apiKey, sol))
    }.flowOn(Dispatchers.IO)
}