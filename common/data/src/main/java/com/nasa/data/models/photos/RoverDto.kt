package com.nasa.data.models.photos


import com.google.gson.annotations.SerializedName

data class RoverDto(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("landing_date")
    val landingDate: String?,
    @SerializedName("launch_date")
    val launchDate: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("status")
    val status: String?
)