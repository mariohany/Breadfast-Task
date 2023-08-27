package com.skeleton.app.feature.posts.domain.usecase

import com.skeleton.app.feature.posts.data.remote.model.Gender
import com.skeleton.app.feature.posts.data.remote.model.Post
import com.skeleton.app.feature.posts.data.remote.model.User
import com.skeleton.app.feature.posts.domain.entities.PostEntity
import com.skeleton.app.feature.posts.domain.repository.IPostsRepository
import com.skeleton.app.feature.posts.domain.repository.IUserDetailsRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class PostsUseCaseTest {

    @Mock
    private lateinit var postsRepo: IPostsRepository

    @Mock
    private lateinit var userRepo: IUserDetailsRepository

    private lateinit var useCase: PostsUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = PostsUseCase(postsRepo, userRepo)
    }

    @Test
    fun `test invoke() method with Success`() = runBlocking {
        val postsApiResponse = listOf(Post(1000L, 1500L, "title", "body"))
        val userApiResponse = User(1500L, "name", "email", Gender.male, "status")
        val expectedResult = Result.success(listOf(PostEntity(1000L, "name", "title", "body")))
        whenever(postsRepo.getPosts()).thenAnswer { postsApiResponse }
        whenever(userRepo.getUserDetails(postsApiResponse.first().userId)).thenAnswer { userApiResponse }

        assertEquals(useCase(), expectedResult)
    }

    @Test
    fun `test invoke() method with Failure`() = runBlocking {
        val throwable = Throwable()
        val postsApiResponse = Result.failure<List<Post>>(throwable)
        val userApiResponse = Result.failure<User>(throwable)
        val expectedResult = Result.failure<List<PostEntity>>(throwable)
        whenever(postsRepo.getPosts()).thenReturn(postsApiResponse)
        whenever(userRepo.getUserDetails(1500L)).thenReturn(userApiResponse)

        assertEquals(useCase(), expectedResult)
    }
}