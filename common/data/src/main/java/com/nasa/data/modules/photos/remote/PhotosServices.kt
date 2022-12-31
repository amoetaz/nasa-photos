package com.nasa.data.modules.photos.remote

import com.nasa.data.models.photos.PhotosResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosServices {

    @GET("rovers/curiosity/photos")
    suspend fun getMarsPhotos(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String,
        @Query("sol") sol: Int,
    ): PhotosResponse
}