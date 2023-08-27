package com.skeleton.app.feature.postdetails.di

import com.skeleton.app.feature.postdetails.data.remote.CommentsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@InstallIn(ViewModelComponent::class)
@Module
class CommentsApiModule {
    @Provides
    fun provideCommentsApi(retrofit: Retrofit): CommentsApi =
        retrofit.create(CommentsApi::class.java)
}