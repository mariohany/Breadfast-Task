package com.skeleton.app.feature.postdetails.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.skeleton.app.databinding.CommentItemBinding
import com.skeleton.app.feature.postdetails.domain.entities.CommentEntity

class CommentsAdapter : ListAdapter<CommentEntity, ViewHolder>(CommentDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CommentItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is CommentViewHolder) {
            holder.bind(getItem(position))
        }
    }

    inner class CommentViewHolder(private val binding: CommentItemBinding) : ViewHolder(binding.root) {
        fun bind(item: CommentEntity) {
            with(binding) {
                userNameTv.text = item.name
                commentTv.text = item.body
            }
        }
    }

    class CommentDiffCallBack : DiffUtil.ItemCallback<CommentEntity>() {
        override fun areItemsTheSame(
            oldItem: CommentEntity,
            newItem: CommentEntity
        ): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: CommentEntity,
            newItem: CommentEntity
        ): Boolean =
            oldItem == newItem
    }
}