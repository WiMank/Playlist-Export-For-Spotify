package com.wimank.pbfs.ui.fragment

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import com.wimank.pbfs.R
import com.wimank.pbfs.databinding.AuthenticationFragmentBinding
import com.wimank.pbfs.viewmodel.AuthenticationViewModel

class AuthenticationFragment : BaseFragment<AuthenticationFragmentBinding>() {

    private val viewModel: AuthenticationViewModel by activityViewModels()

    override fun getLayoutRes() = R.layout.authentication_fragment

    override fun iniView(savedInstanceState: Bundle?) {

    }

    override fun setBindingViewModel() {
        dataBinding.viewModel = viewModel
    }

}
