package com.nasa.data.modules.photos.remote

import com.nasa.data.models.photos.PhotosResponse
import retrofit2.http.GET
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface PhotosServices {

    @GET("rovers/{rover}/photos")
    suspend fun getMarsPhotos(
        @Path("rover") rover: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String,
        @Query("sol") sol: Int,
    ): PhotosResponse
}