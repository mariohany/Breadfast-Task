package com.skeleton.app.feature.splash.presentation.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.navigation.fragment.findNavController
import com.skeleton.app.core.presentation.view.BaseFragment
import com.skeleton.app.databinding.FragmentSplashBinding

class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(SplashFragmentDirections.splashFragmentToPostsFragmentAction())
        }, 2000)
    }
}