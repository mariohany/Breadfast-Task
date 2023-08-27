package com.skeleton.app.feature.posts.domain.usecase

import com.skeleton.app.core.domain.usecase.INoneInputUseCase
import com.skeleton.app.feature.posts.domain.entities.PostEntity
import com.skeleton.app.feature.posts.domain.repository.IPostsRepository
import com.skeleton.app.feature.posts.domain.repository.IUserDetailsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class PostsUseCase @Inject constructor(
    private val postsRepo: IPostsRepository,
    private val userRepo: IUserDetailsRepository
) : INoneInputUseCase<List<PostEntity>> {
    override suspend fun invoke(): Result<List<PostEntity>> = postsRepo.getPosts().map {
        it.map {
            PostEntity(
                id = it.id,
                userName = userRepo.getUserDetails(it.userId).getOrNull()?.name ?: "404 Name Not Found",
                title = it.title,
                body = it.body
            )
        }
    }
}