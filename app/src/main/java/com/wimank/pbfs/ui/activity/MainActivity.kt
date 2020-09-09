package com.wimank.pbfs.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.wimank.pbfs.*
import com.wimank.pbfs.R
import com.wimank.pbfs.databinding.ActivityMainBinding
import com.wimank.pbfs.ui.fragment.AuthenticationFragment
import com.wimank.pbfs.ui.fragment.PlaylistFragment
import com.wimank.pbfs.ui.fragment.UserProfileDialog
import com.wimank.pbfs.ui.utils.UiRouter
import com.wimank.pbfs.util.*
import com.wimank.pbfs.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import net.openid.appauth.*
import timber.log.Timber
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(),
    PlaylistFragment.BackupFragmentCallback,
    AuthenticationFragment.AuthenticationFragmentCallBack,
    UserProfileDialog.UserProfileDialogCallback {

    private val authService by lazy { AuthorizationService(this) }
    private val uiRouter: UiRouter by lazy { UiRouter(findNavController(R.id.main_nav_host)) }
    private val viewModel: MainActivityViewModel by viewModels()
    private val workManager: WorkManager by lazy { WorkManager.getInstance(this) }

    @Inject
    lateinit var connectivityWatcher: ConnectivityWatcher

    override fun iniView(savedInstanceState: Bundle?) {
        setSupportActionBar(dataBinding.mainToolbar)
        initViewExFab()
        startObserve()
        connectivityWatcher()
        observeWorkStatus()
    }

    override fun getLayoutRes() = R.layout.activity_main

    override fun setBindingViewModel() {
        dataBinding.viewModel = viewModel
    }

    private fun initViewExFab() {
        dataBinding.exFabMain.apply {
            setOnClickListener {
                startWork()
            }
        }
    }

    private fun startObserve() {
        viewModel.sessionState.observe(this) {
            setupNavGraph(it)
        }

        viewModel.networkState.observe(this) {
            dataBinding.exFabMain.isEnabled = it
        }
    }

    private fun connectivityWatcher() {
        connectivityWatcher.observe(this) {
            dataBinding.exFabMain.isEnabled = it
        }
    }

    private fun observeWorkStatus() {
        viewModel.workId.observe(this) {
            observeWork(it)
        }
    }

    private fun startWork() {
        workManager.enqueueUniqueWork(
            WORK_TAG,
            ExistingWorkPolicy.KEEP,
            viewModel.createExportWork()
        )
    }

    private fun observeWork(id: UUID) {
        workManager.getWorkInfoByIdLiveData(id).observe(this) {
            when (it?.state) {
                WorkInfo.State.SUCCEEDED -> {
                    dataBinding.exFabMain.clearAnimationAndExtend()
                    showSnackBarFileShare(dataBinding.exFabMain)
                }

                WorkInfo.State.FAILED -> {
                    dataBinding.exFabMain.clearAnimationAndExtend()
                    showSnackBar(dataBinding.exFabMain, R.string.export_error)
                }

                WorkInfo.State.ENQUEUED -> {
                    dataBinding.exFabMain.startExFabAnimation(this)
                }

                WorkInfo.State.RUNNING -> {
                    dataBinding.exFabMain.startExFabAnimation(this)
                }

                else -> {
                    showSnackBar(dataBinding.exFabMain, R.string.track_export_null)
                }
            }
        }
    }

    private fun setupNavGraph(authStart: Boolean) {
        val navGraph = uiRouter.getNavController().navInflater.inflate(R.navigation.app_navigation)
        if (authStart) {
            navGraph.startDestination = R.id.playlistFragment
        } else {
            navGraph.startDestination = R.id.authenticationFragment
        }
        uiRouter.getNavController().graph = navGraph
        initDestinationListener()
    }

    private fun initDestinationListener() {
        uiRouter.getNavController()
            .addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.authenticationFragment -> {
                        dataBinding.exFabMain.hide()
                        dataBinding.mainAppBar.setExpanded(false, false)
                    }
                    else -> {
                        dataBinding.exFabMain.show()
                        dataBinding.mainAppBar.setExpanded(true, true)
                    }
                }
            }
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
                        viewModel.prepareAndWriteSession(response)
                    } else {
                        // authorization failed, check ex for more details
                        Timber.e(ex)
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.export_zip -> {
                ShareZipArchive(this).run {
                    if (zipFileExist()) {
                        share()
                    } else {
                        showSnackBar(dataBinding.exFabMain, R.string.nothing_send)
                    }
                }
            }
            android.R.id.home -> {
                uiRouter.navigateToUserProfileDialog()
            }
        }
        return true
    }

    override fun startAuthentication() {
        requestToken()
    }

    override fun completeAuthentication() {
        uiRouter.navigateToPlaylistFragment()
    }

    override fun startLoad() {
        dataBinding.exFabMain.hide()
    }

    override fun stopLoad(listIsNotEmpty: Boolean) {
        if (listIsNotEmpty) {
            dataBinding.exFabMain.show()
        } else {
            dataBinding.exFabMain.hide()
        }
    }

    override fun logout() {
        uiRouter.navigateToAuthenticationFragment()
    }
}
