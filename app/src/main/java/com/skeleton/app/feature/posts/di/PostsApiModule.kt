package com.skeleton.app.feature.posts.di

import com.skeleton.app.feature.posts.data.remote.api.PostsApi
import com.skeleton.app.feature.posts.data.remote.api.UserDetailsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@InstallIn(ViewModelComponent::class)
@Module
class PostsApiModule {

    @Provides
    fun providePostsApi(retrofit: Retrofit): PostsApi =
        retrofit.create(PostsApi::class.java)

    @Provides
    fun provideUserDetailsApi(retrofit: Retrofit): UserDetailsApi =
        retrofit.create(UserDetailsApi::class.java)
}