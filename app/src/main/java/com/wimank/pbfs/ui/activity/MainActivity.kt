package com.wimank.pbfs.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import com.wimank.pbfs.*
import com.wimank.pbfs.databinding.ActivityMainBinding
import com.wimank.pbfs.ui.fragment.BackupFragment
import com.wimank.pbfs.ui.utils.UiRouter
import com.wimank.pbfs.util.AppPreferencesManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), BackupFragment.BackupFragmentCallback {

    @Inject
    lateinit var appPreferencesManager: AppPreferencesManager
    private val uiRouter: UiRouter by lazy {
        UiRouter(findNavController(R.id.main_nav_host))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    private fun requestToken() {
        val request = getAuthenticationRequest()
        AuthorizationClient.openLoginActivity(this, AUTH_TOKEN_REQUEST_CODE, request)
    }

    private fun getAuthenticationRequest(): AuthorizationRequest? {
        return AuthorizationRequest.Builder(
            CLIENT_ID,
            AuthorizationResponse.Type.TOKEN,
            getRedirectUri().toString()
        )
            .setShowDialog(false)
            .setScopes(arrayOf(AUTH_SCOPE))
            .build()
    }

    private fun getRedirectUri(): Uri? {
        return Uri.parse(REDIRECT_URI)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val response = AuthorizationClient.getResponse(resultCode, data)
        if (AUTH_TOKEN_REQUEST_CODE == requestCode) {
            appPreferencesManager.putToken(response.accessToken)
            //TODO: Удалить это
            Toast.makeText(this, "Token [${response.accessToken}]", Toast.LENGTH_SHORT).show()
        }
    }

    override fun requestAuthentication() {
        requestToken()
    }
}
