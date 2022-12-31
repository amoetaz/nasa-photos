package com.nasa.data.modules.photos.repository


import com.nasa.data.models.photos.toPhoto
import com.nasa.data.models.toDataResource
import com.nasa.data.modules.photos.remote.PhotosRemoteDataSource
import com.nasa.domain.models.Photo
import com.nasa.domain.modules.photos.repository.PhotosRepository
import com.nasa.domain.utils.Resource

import javax.inject.Inject

class PhotosRepositoryImp @Inject constructor(private val photosRemoteDataSource: PhotosRemoteDataSource) :
    PhotosRepository {

    override suspend fun getPhotos(
        rover: String,
        page: Int,
        apiKey: String,
        sol: Int
    ): Resource<List<Photo>?> {
        val resource = photosRemoteDataSource.getPhotos(rover,page, apiKey, sol)
        return if (resource is Resource.Success)
            Resource.Success(resource.value?.photos?.map { it.toPhoto() })
        else resource.toDataResource()
    }


}