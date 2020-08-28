package com.wimank.pbfs.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.wimank.pbfs.*
import com.wimank.pbfs.R
import com.wimank.pbfs.databinding.ActivityMainBinding
import com.wimank.pbfs.ui.fragment.PlaylistFragment
import com.wimank.pbfs.ui.utils.UiRouter
import com.wimank.pbfs.util.AppPreferencesManager
import dagger.hilt.android.AndroidEntryPoint
import net.openid.appauth.*
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), PlaylistFragment.BackupFragmentCallback {

    @Inject
    lateinit var appPreferencesManager: AppPreferencesManager
    private val authService by lazy { AuthorizationService(this) }
    private val uiRouter: UiRouter by lazy {
        UiRouter(findNavController(R.id.main_nav_host))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        requestToken()
    }

    private fun requestToken() {
        startActivityForResult(buildAuthIntent(), AUTH_REQUEST_CODE)
    }

    private fun buildAuthIntent(): Intent {
        return authService.getAuthorizationRequestIntent(
            prepareAuthRequestBuilder()
                .setScopes(SPOTIFY_SCOPES.asIterable())
                .build()
        )
    }

    private fun prepareServiceConfig(): AuthorizationServiceConfiguration {
        return AuthorizationServiceConfiguration(
            Uri.parse(AUTHORIZATION_ENDPOINT),  // authorization endpoint
            Uri.parse(TOKEN_ENDPOINT) // token endpoint
        )
    }

    private fun prepareAuthRequestBuilder(): AuthorizationRequest.Builder {
        return AuthorizationRequest.Builder(
            prepareServiceConfig(),  // the authorization service configuration
            CLIENT_ID,  // the client ID, typically pre-registered and static
            ResponseTypeValues.CODE,  // the response_type value: we want a code
            Uri.parse(REDIRECT_URI) // the redirect URI to which the auth response is sent
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (AUTH_REQUEST_CODE == requestCode) {
            val resp: AuthorizationResponse? = data?.let { AuthorizationResponse.fromIntent(it) }
            Timber.e(AuthorizationException.fromIntent(data))

            resp?.createTokenExchangeRequest()?.let {
                authService.performTokenRequest(it) { response, ex ->
                    if (response != null) {
                        Timber.i("TOKEN ACCESS: ${response.accessToken} ")
                        Timber.i("TOKEN REFRESH: ${response.refreshToken} ")
                        Timber.i("TOKEN ACCESS EXPIRE: ${response.accessTokenExpirationTime} ")
                    } else {
                        // authorization failed, check ex for more details
                        Timber.e(ex)
                    }
                }
            }
        }
    }

    override fun requestAuthentication() {
        requestToken()
    }
}
