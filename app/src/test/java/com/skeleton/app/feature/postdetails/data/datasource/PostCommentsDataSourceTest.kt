package com.skeleton.app.feature.postdetails.data.datasource

import com.skeleton.app.feature.postdetails.data.remote.CommentsApi
import com.skeleton.app.feature.postdetails.data.remote.model.Comment
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class PostCommentsDataSourceTest {

    @Mock
    private lateinit var api: CommentsApi

    private lateinit var dataSource: IPostCommentsDataSource
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        dataSource = PostCommentsDataSource(api)
    }

    @Test
    fun `test getPostComments() method`() = runBlocking {
        val response = listOf(Comment(1L, 1000L, "name", "email", "body"))
        val expectedResult = Result.success(response)
        whenever(api.getPostComments(1000)).thenAnswer { response }

        TestCase.assertEquals(dataSource.getPostComments(1000L), expectedResult)
    }
}