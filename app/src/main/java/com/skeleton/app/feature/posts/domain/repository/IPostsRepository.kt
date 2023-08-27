package com.skeleton.app.feature.posts.domain.repository

import com.skeleton.app.feature.posts.data.remote.model.Post

interface IPostsRepository {
    suspend fun getPosts(): Result<List<Post>>
}