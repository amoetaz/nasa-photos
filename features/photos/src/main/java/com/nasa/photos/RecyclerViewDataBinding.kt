package com.nasa.photos

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nasa.domain.models.Photo

@BindingAdapter("photos_list")
fun RecyclerView.setPhotosList(list: List<Photo>?) {
    (adapter as? PhotosAdapter)?.submitList(list)
}


