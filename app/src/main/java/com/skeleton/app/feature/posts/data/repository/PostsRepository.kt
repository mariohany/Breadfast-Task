package com.skeleton.app.feature.posts.data.repository

import com.skeleton.app.feature.posts.data.remote.datasource.IPostsDataSource
import com.skeleton.app.feature.posts.data.remote.model.Post
import com.skeleton.app.feature.posts.domain.repository.IPostsRepository
import javax.inject.Inject

class PostsRepository @Inject constructor(private val dataSource: IPostsDataSource) :
    IPostsRepository {
    override suspend fun getPosts(): Result<List<Post>> = try {
        dataSource.getPosts()
    }catch (e:Exception){
        Result.failure(e)
    }
}