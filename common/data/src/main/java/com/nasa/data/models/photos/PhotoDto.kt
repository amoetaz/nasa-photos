package com.nasa.data.models.photos


import com.google.gson.annotations.SerializedName

data class PhotoDto(
    @SerializedName("camera")
    val camera: CameraDto?,
    @SerializedName("earth_date")
    val earthDate: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("img_src")
    val imgSrc: String?,
    @SerializedName("rover")
    val rover: RoverDto?,
    @SerializedName("sol")
    val sol: Int?
)