package com.nasa.photos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nasa.domain.models.Photo
import com.nasa.photos.databinding.ItemPhotoBinding


class PhotosAdapter() :
    ListAdapter<Photo, PhotosAdapter.ArticleViewHolder>(ArticleDiffCallback) {

    var onItemClick : (Photo)-> Unit = {}
    inner class ArticleViewHolder(val binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root)

    object ArticleDiffCallback : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem  == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            ItemPhotoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.apply {
            photo = item
            holder.itemView.setOnClickListener {
                onItemClick(item)
            }
        }
    }

}