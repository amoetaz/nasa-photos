package com.nasa.data.models

import android.os.Build
import android.text.Html
import android.text.Spanned
import com.nasa.domain.utils.Resource


fun <T, R> Resource<T>.toDataResource(): Resource<R> where R : Any {
    return when (this) {
        is Resource.Failure -> {
            this
        }
        is Resource.Loading -> {
            this
        }
        else -> {
            Resource.Default
        }
    }
}