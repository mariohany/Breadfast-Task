package com.skeleton.app.feature.posts.data.remote.datasource

import com.skeleton.app.feature.posts.data.remote.api.UserDetailsApi
import com.skeleton.app.feature.posts.data.remote.model.User
import javax.inject.Inject

class UserDetailsDataSource @Inject constructor(private val api: UserDetailsApi):
    IUserDetailsDataSource {
    override suspend fun getUserDetails(userId: Long): Result<User> = api.getUserDetails(userId)
}