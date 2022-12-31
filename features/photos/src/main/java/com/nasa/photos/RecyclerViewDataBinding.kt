package com.nasa.photos

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nasa.domain.models.Photo

@BindingAdapter("articles_list")
fun RecyclerView.setArticlesList(list: List<Photo>?) {
    Log.d("sfdsfsddsf", "setArticlesList: $list")
    (adapter as? ArticlesAdapter)?.submitList(list)
}


