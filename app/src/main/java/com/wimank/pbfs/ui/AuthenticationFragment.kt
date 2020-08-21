package com.wimank.pbfs.ui

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.wimank.pbfs.R
import com.wimank.pbfs.databinding.AuthenticationFragmentBinding
import com.wimank.pbfs.viewmodel.AuthenticationViewModel


class AuthenticationFragment : BaseFragment<AuthenticationFragmentBinding>() {

    private val viewModel: AuthenticationViewModel by viewModels()

    override fun getLayoutRes() = R.layout.authentication_fragment

    override fun iniView(savedInstanceState: Bundle?) {

    }

    override fun setBindingViewModel() {

    }
}
