package com.skeleton.app.feature.postdetails.presentation.viewmodel

import com.skeleton.app.feature.postdetails.domain.entities.CommentEntity
import com.skeleton.app.feature.posts.domain.entities.PostEntity


data class PostDetailsScreenState(
    val isLoading:Boolean = false,
    val post:PostEntity? = null,
    val commentsList:List<CommentEntity> = emptyList(),
)
