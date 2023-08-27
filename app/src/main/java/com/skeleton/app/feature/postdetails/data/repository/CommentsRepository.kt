package com.skeleton.app.feature.postdetails.data.repository

import com.skeleton.app.feature.postdetails.data.datasource.IPostCommentsDataSource
import com.skeleton.app.feature.postdetails.domain.entities.CommentEntity
import com.skeleton.app.feature.postdetails.domain.repository.ICommentsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class CommentsRepository @Inject constructor(private val postCommentsDataSource: IPostCommentsDataSource) :
    ICommentsRepository {
    override suspend fun getPostComments(postId: Long): Result<List<CommentEntity>> =
        postCommentsDataSource.getPostComments(postId).map {
            it.map { CommentEntity(it.id, it.name, it.body) }
        }
}