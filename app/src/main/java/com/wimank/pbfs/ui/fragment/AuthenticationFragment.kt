package com.wimank.pbfs.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.viewModels
import com.wimank.pbfs.R
import com.wimank.pbfs.databinding.AuthenticationFragmentBinding
import com.wimank.pbfs.di.AuthModule
import com.wimank.pbfs.util.showSnackBar
import com.wimank.pbfs.viewmodel.AuthenticationViewModel
import dagger.hilt.android.AndroidEntryPoint
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationResponse
import net.openid.appauth.AuthorizationService
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class AuthenticationFragment : BaseFragment<AuthenticationFragmentBinding>() {

    @VisibleForTesting
    val viewModel: AuthenticationViewModel by viewModels()
    private var authenticationFragmentCallBack: AuthenticationFragmentCallBack? = null

    @Inject
    lateinit var authService: AuthorizationService

    @Inject
    @Named(AuthModule.AUTH_INTENT)
    lateinit var authIntent: Intent

    private val startAuthIntent = registerForActivityResult(StartActivityForResult()) {
        handleAuthorizationData(it.data)
    }

    override fun iniView(savedInstanceState: Bundle?) {
        dataBinding.startAuth.setOnClickListener {
            startAuthIntent.launch(authIntent)
        }

        dataBinding.ok.setOnClickListener {
            authenticationFragmentCallBack?.completeAuthentication()
        }

        viewModel.authError.observe(this) {
            it.getContentIfNotHandled()?.let { message ->
                showSnackBar(dataBinding.root, message)
            }
        }
    }

    override fun getLayoutRes() = R.layout.authentication_fragment

    override fun setBindingViewModel() {
        dataBinding.viewModel = viewModel
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is AuthenticationFragmentCallBack)
            authenticationFragmentCallBack = context
    }

    private fun handleAuthorizationData(data: Intent?) {
        val resp: AuthorizationResponse? = data?.let { AuthorizationResponse.fromIntent(it) }
        Timber.e(AuthorizationException.fromIntent(data))
        resp?.createTokenExchangeRequest()?.let {
            authService.performTokenRequest(it) { response, ex ->
                if (response != null) {
                    //authorization success, save session
                    viewModel.prepareAndWriteSession(response)
                } else {
                    // authorization failed, check ex for more details
                    Timber.e(ex)
                }
            }
        }
    }

    interface AuthenticationFragmentCallBack {
        fun completeAuthentication()
    }
}
