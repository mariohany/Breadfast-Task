package com.skeleton.app.feature.posts.data.remote.datasource

import com.skeleton.app.feature.posts.data.remote.model.Post

interface IPostsDataSource {
    suspend fun getPosts(): Result<List<Post>>
}