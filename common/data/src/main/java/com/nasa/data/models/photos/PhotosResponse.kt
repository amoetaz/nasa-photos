package com.nasa.data.models.photos


import com.google.gson.annotations.SerializedName

data class PhotosResponse(
    @SerializedName("photos")
    val photos: List<PhotoDto>?
)