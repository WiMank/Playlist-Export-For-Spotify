package com.wimank.pbfs.ui.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Base class for activities with Data Binding.
 * */
abstract class BaseActivity<DB : ViewDataBinding> : AppCompatActivity() {

    internal lateinit var dataBinding: DB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, getLayoutRes())
        dataBinding.lifecycleOwner = this
        iniView(savedInstanceState)
        setBindingViewModel()
    }

    @LayoutRes
    abstract fun getLayoutRes(): Int

    /**
     * Method called after data binding. Replaces onCreate.
     * */
    abstract fun iniView(savedInstanceState: Bundle?)

    /**
     * Initializing the viewmodel in the activity layout.
     * */
    abstract fun setBindingViewModel()
}
