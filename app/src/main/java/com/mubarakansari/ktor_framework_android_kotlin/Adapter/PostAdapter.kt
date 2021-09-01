package com.mubarakansari.ktor_framework_android_kotlin.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mubarakansari.ktor_framework_android_kotlin.Model.Post
import com.mubarakansari.ktor_framework_android_kotlin.R
import com.mubarakansari.ktor_framework_android_kotlin.databinding.EachRowBinding
import com.squareup.picasso.Picasso
import javax.inject.Inject

class PostAdapter @Inject constructor() : ListAdapter<Post, PostAdapter.PostViewHolder>(diff) {

    class PostViewHolder(private val binding: EachRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(post: Post) {

            Picasso.get().load(post.url).centerCrop().fit().into(binding.imageView)
            binding.textViewTitle.text = post.title

        }
    }

    object diff : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        post?.let {
            holder.bindData(post)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return (PostViewHolder(
            EachRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ))
    }
}