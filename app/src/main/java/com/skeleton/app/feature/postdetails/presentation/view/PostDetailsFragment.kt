package com.skeleton.app.feature.postdetails.presentation.view

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import com.skeleton.app.core.presentation.view.BaseFragment
import com.skeleton.app.databinding.FragmentPostDetailsBinding
import com.skeleton.app.feature.postdetails.presentation.adapter.CommentsAdapter
import com.skeleton.app.feature.postdetails.presentation.viewmodel.PostDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostDetailsFragment :
    BaseFragment<FragmentPostDetailsBinding>(FragmentPostDetailsBinding::inflate) {

    private val viewModel: PostDetailsViewModel by viewModels()
    private val commentsAdapter = CommentsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        observeUiState()
    }

    private fun setupUI() {
        with(binding) {
            commentsRv.adapter = commentsAdapter
            commentsRv.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    private fun observeUiState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    with(binding) {
                        userNameTv.text = state.post?.userName
                        titleTv.text = state.post?.title
                        bodyTv.text = state.post?.body
                        progress.isVisible = state.isLoading
                        commentsAdapter.submitList(state.commentsList)
                        commentsRv.isVisible = state.commentsList.isNotEmpty() && !state.isLoading
                    }
                }
            }
        }
    }
}