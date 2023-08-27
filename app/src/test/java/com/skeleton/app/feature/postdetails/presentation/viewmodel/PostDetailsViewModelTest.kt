package com.skeleton.app.feature.postdetails.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.skeleton.app.MainCoroutineRule
import com.skeleton.app.feature.postdetails.domain.repository.ICommentsRepository
import com.skeleton.app.feature.posts.domain.entities.PostEntity
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

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
        viewModel.uiState.test {
            assertEquals(state.copy(isLoading = true), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `test initial state getPostComments() Failure`() = runBlocking {

    }
}