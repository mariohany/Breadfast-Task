package com.skeleton.app.feature.posts.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.skeleton.app.databinding.PostItemBinding
import com.skeleton.app.feature.posts.domain.entities.PostEntity

class PostsAdapter : ListAdapter<PostEntity, ViewHolder>(PostDiffCallBack()) {

    private var listener: ((PostEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PostItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is PostViewHolder) {
            holder.bind(getItem(position))
        }
    }

    inner class PostViewHolder(private val binding: PostItemBinding) : ViewHolder(binding.root) {
        fun bind(item: PostEntity) {
            with(binding) {
                userNameTv.text = item.userName
                titleTv.text = item.title
                bodyTv.text = item.body

                root.setOnClickListener {
                    listener?.invoke(item)
                }
            }
        }
    }

    fun setOnPostClicked(listener: (PostEntity) -> Unit) {
        this.listener = listener
    }

    class PostDiffCallBack : DiffUtil.ItemCallback<PostEntity>() {
        override fun areItemsTheSame(
            oldItem: PostEntity,
            newItem: PostEntity
        ): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: PostEntity,
            newItem: PostEntity
        ): Boolean =
            oldItem == newItem
    }
}