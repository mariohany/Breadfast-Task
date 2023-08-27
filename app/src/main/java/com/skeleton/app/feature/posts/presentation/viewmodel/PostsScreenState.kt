package com.skeleton.app.feature.posts.presentation.viewmodel

import com.skeleton.app.feature.posts.domain.entities.PostEntity

data class PostsScreenState(
    val isLoading:Boolean = false,
    val postsList:List<PostEntity> = emptyList(),
    val errorState:Boolean = false
)
