package com.skeleton.app.feature.posts.data.remote.api

import com.skeleton.app.feature.posts.data.remote.model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface UserDetailsApi {

    @GET("public/v2/users/{userId}")
    suspend fun getUserDetails(@Path("userId") userId: Long): Result<User>
}