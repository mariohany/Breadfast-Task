package com.skeleton.app.feature.posts.data.remote.datasource

import com.skeleton.app.feature.posts.data.remote.model.User

interface IUserDetailsDataSource {
    suspend fun getUserDetails(userId: Long): Result<User>
}