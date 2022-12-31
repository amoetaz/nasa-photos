package com.nasa.data.models


import com.google.gson.annotations.SerializedName

data class ArticlesResponse(
    @SerializedName("data")
    val `data`: ArticlesInfoDto?
) : BaseResponse()