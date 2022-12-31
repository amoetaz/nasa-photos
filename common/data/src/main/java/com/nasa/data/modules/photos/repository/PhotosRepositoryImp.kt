package com.nasa.data.modules.photos.repository


import com.nasa.data.models.photos.toPhoto
import com.nasa.data.models.toDataResource
import com.nasa.data.modules.photos.remote.ArticlesRemoteDataSource
import com.nasa.domain.models.Photo
import com.nasa.domain.modules.photos.repository.PhotosRepository
import com.nasa.domain.utils.Resource

import javax.inject.Inject

class PhotosRepositoryImp @Inject constructor(private val articlesRemoteDataSource: ArticlesRemoteDataSource) :
    PhotosRepository {

    override suspend fun getPhotos(
        page: Int,
        apiKey: String,
        sol: Int
    ): Resource<List<Photo>?> {
        val resource = articlesRemoteDataSource.getPhotos(page, apiKey, sol)
        return if (resource is Resource.Success)
            Resource.Success(resource.value?.photos?.map { it.toPhoto() })
        else resource.toDataResource()
    }


}