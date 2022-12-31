package com.nasa.data.models.photos


import com.google.gson.annotations.SerializedName

data class CameraDto(
    @SerializedName("full_name")
    val fullName: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("rover_id")
    val roverId: Int?
)