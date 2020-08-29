package com.wimank.pbfs.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject


abstract class BaseFragment<DB : ViewDataBinding> : Fragment() {

    @Inject
    //@Named("FragmentViewModelFactory")
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
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
    }

    @LayoutRes
    abstract fun getLayoutRes(): Int

    abstract fun iniView(savedInstanceState: Bundle?)

    abstract fun setBindingViewModel()

}
