package com.nasa.domain.modules.photos.repository

import com.nasa.domain.models.Photo
import com.nasa.domain.utils.Resource

interface PhotosRepository {

    suspend fun getPhotos(rover : String ,page: Int, apiKey: String, sol: Int): Resource<List<Photo>?>
}