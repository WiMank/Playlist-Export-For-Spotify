package com.wimank.pbfs.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.viewModels
import com.wimank.pbfs.R
import com.wimank.pbfs.databinding.AuthenticationFragmentBinding
import com.wimank.pbfs.viewmodel.AuthenticationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthenticationFragment : BaseFragment<AuthenticationFragmentBinding>() {

    private val viewModel: AuthenticationViewModel by viewModels()
    private var authenticationFragmentCallBack: AuthenticationFragmentCallBack? = null

    override fun getLayoutRes() = R.layout.authentication_fragment

    override fun iniView(savedInstanceState: Bundle?) {

        dataBinding.startAuth.setOnClickListener {
            authenticationFragmentCallBack?.startAuthentication()
        }

        dataBinding.ok.setOnClickListener {
            authenticationFragmentCallBack?.completeAuthentication()
        }
    }

    override fun setBindingViewModel() {
        dataBinding.viewModel = viewModel
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is AuthenticationFragmentCallBack)
            authenticationFragmentCallBack = context
    }

    interface AuthenticationFragmentCallBack {
        fun startAuthentication()
        fun completeAuthentication()
    }
}
