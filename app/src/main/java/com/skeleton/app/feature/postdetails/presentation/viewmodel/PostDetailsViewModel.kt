package com.skeleton.app.feature.postdetails.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skeleton.app.feature.postdetails.domain.repository.ICommentsRepository
import com.skeleton.app.feature.postdetails.presentation.view.PostDetailsFragmentArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val commentsRepo: ICommentsRepository
) : ViewModel() {
    private val args = PostDetailsFragmentArgs.fromSavedStateHandle(savedStateHandle)
    private val _uiState = MutableStateFlow(PostDetailsScreenState(post = args.post))
    val uiState: StateFlow<PostDetailsScreenState> = _uiState

    init {
        loadPostComments()
    }

    private fun loadPostComments() = viewModelScope.launch {
        _uiState.emit(uiState.value.copy(isLoading = true))
        commentsRepo.getPostComments(args.post.id).fold({
            _uiState.emit(
                uiState.value.copy(
                    isLoading = false,
                    commentsList = it
                )
            )
        }, {
            _uiState.emit(
                uiState.value.copy(
                    isLoading = false,
                )
            )
        })
    }
}