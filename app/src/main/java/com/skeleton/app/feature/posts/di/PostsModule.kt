package com.skeleton.app.feature.posts.di

import com.skeleton.app.feature.posts.data.remote.datasource.IPostsDataSource
import com.skeleton.app.feature.posts.data.remote.datasource.IUserDetailsDataSource
import com.skeleton.app.feature.posts.data.remote.datasource.PostsDataSource
import com.skeleton.app.feature.posts.data.remote.datasource.UserDetailsDataSource
import com.skeleton.app.feature.posts.data.repository.PostsRepository
import com.skeleton.app.feature.posts.data.repository.UserDetailsRepository
import com.skeleton.app.feature.posts.domain.repository.IPostsRepository
import com.skeleton.app.feature.posts.domain.repository.IUserDetailsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class PostsModule {
    @Binds
    abstract fun bindPostsDataSource(dataSource: PostsDataSource): IPostsDataSource

    @Binds
    abstract fun bindUserDetailsDataSource(dataSource: UserDetailsDataSource): IUserDetailsDataSource

    @Binds
    abstract fun bindPostsRepository(repository: PostsRepository): IPostsRepository

    @Binds
    abstract fun bindUserDetailsRepository(repository: UserDetailsRepository): IUserDetailsRepository
}