package com.skeleton.app.feature.posts.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skeleton.app.feature.posts.domain.usecase.PostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val postsUseCase: PostsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(PostsScreenState())
    val uiState: StateFlow<PostsScreenState> = _uiState

    init {
        getPosts()
    }

    private fun getPosts() = viewModelScope.launch {
        _uiState.emit(uiState.value.copy(isLoading = true))
        postsUseCase().fold({
            _uiState.emit(
                uiState.value.copy(
                    isLoading = false,
                    postsList = it
                )
            )
        }, {
            _uiState.emit(
                uiState.value.copy(
                    isLoading = false,
                    errorState = true
                )
            )
        })
    }
}