package com.skeleton.app.feature.postdetails.di

import com.skeleton.app.feature.postdetails.data.datasource.IPostCommentsDataSource
import com.skeleton.app.feature.postdetails.data.datasource.PostCommentsDataSource
import com.skeleton.app.feature.postdetails.data.repository.CommentsRepository
import com.skeleton.app.feature.postdetails.domain.repository.ICommentsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class CommentsModule {
    @Binds
    abstract fun bindPostCommentsDataSource(dataSource: PostCommentsDataSource): IPostCommentsDataSource

    @Binds
    abstract fun bindPostCommentsRepository(repository: CommentsRepository): ICommentsRepository
}