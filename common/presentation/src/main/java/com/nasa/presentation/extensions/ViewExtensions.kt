package com.nasa.presentation.extensions

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.onLastItemReached(lastItemReachedListener: () -> Unit) {
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val manager = this@onLastItemReached.layoutManager as LinearLayoutManager
            val position = manager.findLastVisibleItemPosition()
            if (position > (manager.itemCount * 3 / 4)) {
                lastItemReachedListener()
            }
        }
    })
}
