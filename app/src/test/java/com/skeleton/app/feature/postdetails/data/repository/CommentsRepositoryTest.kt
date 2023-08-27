package com.skeleton.app.feature.postdetails.data.repository

import com.skeleton.app.feature.postdetails.data.datasource.IPostCommentsDataSource
import com.skeleton.app.feature.postdetails.data.remote.model.Comment
import com.skeleton.app.feature.postdetails.domain.entities.CommentEntity
import com.skeleton.app.feature.postdetails.domain.repository.ICommentsRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class CommentsRepositoryTest {

    @Mock
    private lateinit var dataSource: IPostCommentsDataSource

    private lateinit var repository: ICommentsRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = CommentsRepository(dataSource)
    }

    @Test
    fun `test getPostComments() method`() = runBlocking {
        val response = listOf(Comment(1L, 1000L, "name","email", "body"))
        val mappedResponse = response.map { CommentEntity(it.id, it.name, it.body) }
        val expectedResult = Result.success(mappedResponse)
        whenever(dataSource.getPostComments(1000)).thenAnswer { response }

        assertEquals(repository.getPostComments(1000), expectedResult)
    }
}