package com.skeleton.app.feature.posts.data.repository

import com.skeleton.app.feature.posts.data.remote.datasource.IUserDetailsDataSource
import com.skeleton.app.feature.posts.data.remote.model.User
import com.skeleton.app.feature.posts.domain.repository.IUserDetailsRepository
import javax.inject.Inject

class UserDetailsRepository @Inject constructor(private val dataSource: IUserDetailsDataSource) :
    IUserDetailsRepository {
    override suspend fun getUserDetails(userId: Long): Result<User> = dataSource.getUserDetails(userId)
}