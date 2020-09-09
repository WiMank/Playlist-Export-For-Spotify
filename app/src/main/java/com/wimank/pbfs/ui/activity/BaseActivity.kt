package com.wimank.pbfs.ui.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

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

    abstract fun iniView(savedInstanceState: Bundle?)

    abstract fun setBindingViewModel()
}
