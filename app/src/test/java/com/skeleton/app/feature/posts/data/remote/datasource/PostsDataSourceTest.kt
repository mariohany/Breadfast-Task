package com.skeleton.app.feature.posts.data.remote.datasource

import com.skeleton.app.feature.posts.data.remote.api.PostsApi
import com.skeleton.app.feature.posts.data.remote.model.Post
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class PostsDataSourceTest {

    @Mock
    private lateinit var api: PostsApi

    private lateinit var postDataSource: IPostsDataSource

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        postDataSource = PostsDataSource(api)
    }

    @Test
    fun `test getPosts() function`() = runBlocking {
        val response = listOf(Post(0L, 0L, "", ""))
        val expectedResult = Result.success(response)
        whenever(api.getPosts()).thenAnswer { response }

        assertEquals(postDataSource.getPosts(), expectedResult)
    }
}