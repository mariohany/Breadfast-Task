package com.skeleton.app.feature.postdetails.domain.repository

import com.skeleton.app.feature.postdetails.domain.entities.CommentEntity

interface ICommentsRepository {
    suspend fun getPostComments(postId:Long): Result<List<CommentEntity>>
}