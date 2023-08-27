package com.skeleton.app.feature.posts.data.remote.datasource

import com.skeleton.app.feature.posts.data.remote.api.PostsApi
import com.skeleton.app.feature.posts.data.remote.model.Post
import javax.inject.Inject

class PostsDataSource @Inject constructor(private val api: PostsApi): IPostsDataSource {
    override suspend fun getPosts(): Result<List<Post>> = api.getPosts()
}