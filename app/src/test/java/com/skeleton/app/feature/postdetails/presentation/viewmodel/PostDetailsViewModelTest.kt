package com.skeleton.app.feature.postdetails.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.skeleton.app.MainCoroutineRule
import com.skeleton.app.feature.postdetails.domain.entities.CommentEntity
import com.skeleton.app.feature.postdetails.domain.repository.ICommentsRepository
import com.skeleton.app.feature.posts.domain.entities.PostEntity
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PostDetailsViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var repository: ICommentsRepository

    private val savedStateHandle = SavedStateHandle()

    private lateinit var viewModel: PostDetailsViewModel
    private val post = PostEntity(1000L, "name", "title", "body")

    @Before
    fun setUp() {
        savedStateHandle["post"] = post
        MockitoAnnotations.openMocks(this)
        viewModel = PostDetailsViewModel(savedStateHandle, repository)
    }

    @Test
    fun `test initial state getPostComments() Success`() = runBlocking {
        val state = PostDetailsScreenState(post = post)
        val response = listOf(CommentEntity(1000L, "name", "body"))
        whenever(repository.getPostComments(post.id)).thenAnswer { response }
        coroutineScope {
            assertEquals(state.copy(isLoading = false), viewModel.uiState.first())
            viewModel.loadPostComments()
        }
        viewModel.uiState.test {
            assertEquals(state.copy(isLoading = false, commentsList = response), awaitItem())
        }
    }

    @Test
    fun `test initial state getPostComments() Failure`() = runBlocking {
        val state = PostDetailsScreenState(post = post)
        val response = Throwable()
        whenever(repository.getPostComments(post.id)).thenReturn(Result.failure(response))
        coroutineScope {
            assertEquals(state.copy(isLoading = false), viewModel.uiState.first())
            viewModel.loadPostComments()
        }
        viewModel.uiState.test {
            assertEquals(state.copy(isLoading = false), awaitItem())
        }
    }
}