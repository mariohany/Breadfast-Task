package com.skeleton.app.feature.posts.presentation.viewmodel

import app.cash.turbine.test
import com.skeleton.app.MainCoroutineRule
import com.skeleton.app.feature.posts.domain.entities.PostEntity
import com.skeleton.app.feature.posts.domain.usecase.PostsUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

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
//        doAnswer { Result.success(response) }.`when`(useCase).invoke()
        viewModel.uiState.test {
            assertEquals(PostsScreenState(isLoading = true), awaitItem())
        }
    }

    @Test
    fun `test initial state getPosts() Failure`() = runBlocking {

    }
}