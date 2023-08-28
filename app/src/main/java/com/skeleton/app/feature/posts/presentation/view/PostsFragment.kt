package com.skeleton.app.feature.posts.presentation.view

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.skeleton.app.core.presentation.view.BaseFragment
import com.skeleton.app.databinding.FragmentPostsBinding
import com.skeleton.app.feature.posts.presentation.adapter.PostsAdapter
import com.skeleton.app.feature.posts.presentation.viewmodel.PostsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class PostsFragment : BaseFragment<FragmentPostsBinding>(FragmentPostsBinding::inflate) {

    private val viewModel: PostsViewModel by viewModels()
    private val postsAdapter = PostsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getPosts()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        observeUiState()
    }

    private fun setupUI() {
        with(binding) {
            postsRv.adapter = postsAdapter
            postsAdapter.setOnPostClicked { post ->
                findNavController().navigate(
                    PostsFragmentDirections.postsFragmentToPostDetailsFragmentAction(
                        post
                    )
                )
            }
        }
    }

    private fun observeUiState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    with(binding) {
                        postsAdapter.submitList(state.postsList)
                        shimmerLayout.isVisible = state.isLoading
                        shimmerLayout.showShimmer(state.isLoading)
                        if (state.postsList.isNotEmpty() && !state.isLoading) {
                            postsRv.animate().alpha(1f).setDuration(750).withStartAction {
                                postsRv.isVisible = true
                            }.start()
                        }else{
                            postsRv.isVisible = false
                        }
                    }
                }
            }
        }
    }
}