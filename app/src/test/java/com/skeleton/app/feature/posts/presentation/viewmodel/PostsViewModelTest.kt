package com.skeleton.app.feature.posts.presentation.viewmodel

import app.cash.turbine.test
import com.skeleton.app.MainCoroutineRule
import com.skeleton.app.feature.posts.domain.entities.PostEntity
import com.skeleton.app.feature.posts.domain.usecase.PostsUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
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
class PostsViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var useCase: PostsUseCase

    private lateinit var viewModel: PostsViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = PostsViewModel(useCase)
    }

    @Test
    fun `test initial state getPosts() Success`() = runTest {
        val response = listOf(PostEntity(1000L, "name", "title", "body"))
        whenever(useCase()).thenAnswer{response}
        coroutineScope {
            viewModel.getPosts()
        }
        viewModel.uiState.test {
            assertEquals(PostsScreenState(isLoading = false, postsList = response), awaitItem())
        }
    }

    @Test
    fun `test initial state getPosts() Failure`() = runBlocking {
//        val response = listOf(PostEntity(1000L, "name", "title", "body"))
//        whenever(useCase()).thenReturn(){response}
//        coroutineScope {
//            viewModel.getPosts()
//        }
//        viewModel.uiState.test {
//            assertEquals(PostsScreenState(isLoading = false, postsList = response), awaitItem())
//        }
    }
}