package com.nasa.presentation.dataBinding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImageUrl")
fun ImageView.loadImageUrl(url: String?) {
    url?.let {
        Glide.with(context).load("$url")
            .error("$url")
            .into(this)
    }
}


@BindingAdapter("toggleVisibility")
fun View.toggleVisibility(show: Boolean) {

    visibility = if (show) View.VISIBLE else View.GONE
}

