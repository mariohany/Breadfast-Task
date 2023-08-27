package com.skeleton.app.feature.postdetails.data.remote

import com.skeleton.app.feature.postdetails.data.remote.model.Comment
import retrofit2.http.GET
import retrofit2.http.Path

interface CommentsApi {
    @GET("public/v2/posts/{postId}/comments")
    suspend fun getPostComments(@Path("postId") postId: Long): Result<List<Comment>>
}