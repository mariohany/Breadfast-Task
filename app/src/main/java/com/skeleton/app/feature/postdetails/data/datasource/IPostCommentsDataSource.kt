package com.skeleton.app.feature.postdetails.data.datasource

import com.skeleton.app.feature.postdetails.data.remote.model.Comment

interface IPostCommentsDataSource {
    suspend fun getPostComments(postId: Long): Result<List<Comment>>
}