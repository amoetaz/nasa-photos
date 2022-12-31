package com.nasa.data.modules.photos.remote


import com.nasa.data.BaseRemoteDataSource


import javax.inject.Inject

class PhotosRemoteDataSource @Inject constructor(private val apiServices: PhotosServices) :
    BaseRemoteDataSource() {

    suspend fun getPhotos(
        rover : String,
        page: Int,
        apiKey: String,
        sol: Int,
    ) = safeApiCall {
        apiServices.getMarsPhotos(rover,page , apiKey,sol)
    }


}