package com.nasa.presentation.dataBinding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

@BindingAdapter("loadImageUrl")
fun ImageView.loadImageUrl(url: String?) {
    url?.let {
        Glide.with(context).load("$url")
            .error("$url")
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(this)
    }
}


@BindingAdapter("toggleVisibility")
fun View.toggleVisibility(show: Boolean) {

    visibility = if (show) View.VISIBLE else View.GONE
}

