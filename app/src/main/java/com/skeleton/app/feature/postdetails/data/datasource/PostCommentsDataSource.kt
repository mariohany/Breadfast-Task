package com.skeleton.app.feature.postdetails.data.datasource

import com.skeleton.app.feature.postdetails.data.remote.CommentsApi
import com.skeleton.app.feature.postdetails.data.remote.model.Comment
import javax.inject.Inject

class PostCommentsDataSource @Inject constructor(private val api: CommentsApi): IPostCommentsDataSource {
    override suspend fun getPostComments(postId: Long): Result<List<Comment>> = api.getPostComments(postId)
}