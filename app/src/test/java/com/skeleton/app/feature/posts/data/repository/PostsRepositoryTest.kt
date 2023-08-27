package com.skeleton.app.feature.posts.data.repository

import com.skeleton.app.feature.posts.data.remote.datasource.IPostsDataSource
import com.skeleton.app.feature.posts.data.remote.model.Post
import com.skeleton.app.feature.posts.domain.repository.IPostsRepository
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class PostsRepositoryTest {

    @Mock
    private lateinit var dataSource: IPostsDataSource

    private lateinit var repository: IPostsRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = PostsRepository(dataSource)
    }

    @Test
    fun `test getPosts() function`() = runBlocking {
        val response = listOf(Post(0L, 0L, "", ""))
        val expectedResult = Result.success(response)
        whenever(dataSource.getPosts()).thenAnswer { response }

        TestCase.assertEquals(repository.getPosts(), expectedResult)
    }
}