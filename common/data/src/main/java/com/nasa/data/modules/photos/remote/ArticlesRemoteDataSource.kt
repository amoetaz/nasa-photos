package com.nasa.data.modules.photos.remote


import com.nasa.data.BaseRemoteDataSource
import retrofit2.http.Query


import javax.inject.Inject

class ArticlesRemoteDataSource @Inject constructor(private val apiServices: PhotosServices) :
    BaseRemoteDataSource() {

    suspend fun getPhotos(
        page: Int,
        apiKey: String,
        sol: Int,
    ) = safeApiCall {
        apiServices.getMarsPhotos(page , apiKey,sol)
    }


}