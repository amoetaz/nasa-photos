package com.nasa.data.models


import com.google.gson.annotations.SerializedName

data class MediaDto(
    @SerializedName("oembed")
    val oembed: OembedDto?,
    @SerializedName("type")
    val type: String?
)