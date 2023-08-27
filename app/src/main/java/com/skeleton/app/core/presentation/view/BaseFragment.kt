package com.skeleton.app.core.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding


typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding>(private val inflate: Inflate<VB>) : Fragment() {

    private var internalDataBinding: VB? = null
    protected val binding: VB
        get() {
            return internalDataBinding ?: throw IllegalStateException(
                "data binding should not be requested before onViewCreated is called and after on destroyView called"
            )
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        internalDataBinding = inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        internalDataBinding = null
        super.onDestroyView()
    }
}