package com.wimank.pbfs.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * Base class for fragments with Data Binding.
 */
abstract class BaseFragment<DB : ViewDataBinding> : Fragment() {

    internal lateinit var dataBinding: DB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.let { createBinding(inflater, it) }
        iniView(savedInstanceState)
        setBindingViewModel()
        return dataBinding.root
    }

    private fun createBinding(inflater: LayoutInflater, container: ViewGroup) {
        dataBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
        dataBinding.lifecycleOwner = this
    }

    @LayoutRes
    abstract fun getLayoutRes(): Int

    /**
     * Method called after data binding. Replaces onCreateView.
     */
    abstract fun iniView(savedInstanceState: Bundle?)

    /**
     * Initializing the viewmodel in the fragment layout.
     */
    abstract fun setBindingViewModel()

}
