package com.skeleton.app.feature.posts.domain.repository

import com.skeleton.app.feature.posts.data.remote.model.User

interface IUserDetailsRepository {
    suspend fun getUserDetails(userId: Long): Result<User>
}