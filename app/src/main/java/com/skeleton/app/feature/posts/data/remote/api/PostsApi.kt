package com.skeleton.app.feature.posts.data.remote.api

import com.skeleton.app.feature.posts.data.remote.model.Post
import retrofit2.http.GET

interface PostsApi {
    @GET("public/v2/posts")
    suspend fun getPosts(): Result<List<Post>>
}