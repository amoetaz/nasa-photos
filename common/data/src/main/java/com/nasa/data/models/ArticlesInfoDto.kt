package com.nasa.data.models


import com.google.gson.annotations.SerializedName

data class ArticlesInfoDto(
    @SerializedName("after")
    val after: String?,
    @SerializedName("before")
    val before: Any?,
    @SerializedName("children")
    val children: List<ArticleInfoDto>?,
    @SerializedName("dist")
    val dist: Int?,
    @SerializedName("geo_filter")
    val geoFilter: Any?,
    @SerializedName("modhash")
    val modhash: String?
)