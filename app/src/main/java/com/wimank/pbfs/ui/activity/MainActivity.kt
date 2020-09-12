package com.wimank.pbfs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.wimank.pbfs.R
import com.wimank.pbfs.databinding.ActivityMainBinding
import com.wimank.pbfs.di.AuthModule.Companion.AUTH_INTENT
import com.wimank.pbfs.ui.fragment.AuthenticationFragment
import com.wimank.pbfs.ui.fragment.PlaylistFragment
import com.wimank.pbfs.ui.fragment.UserProfileDialog
import com.wimank.pbfs.ui.utils.UiRouter
import com.wimank.pbfs.util.*
import com.wimank.pbfs.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationResponse
import net.openid.appauth.AuthorizationService
import timber.log.Timber
import java.util.*
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(),
    PlaylistFragment.BackupFragmentCallback,
    AuthenticationFragment.AuthenticationFragmentCallBack,
    UserProfileDialog.UserProfileDialogCallback {

    private val uiRouter: UiRouter by lazy { UiRouter(findNavController(R.id.main_nav_host)) }
    private val viewModel: MainActivityViewModel by viewModels()
    private val workManager: WorkManager by lazy { WorkManager.getInstance(this) }

    @Inject
    lateinit var authService: AuthorizationService

    @Inject
    @Named(AUTH_INTENT)
    lateinit var authIntent: Intent

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
        //checking the session status
        viewModel.sessionState.observe(this) {
            setupNavGraph(it)
        }
    }

    private fun connectivityWatcher() {
        //observe the network status and change the button status
        connectivityWatcher.observe(this) {
            dataBinding.exFabMain.isEnabled = it
        }
    }

    private fun observeWorkStatus() {
        //liveData, which contains the working ID for monitoring
        viewModel.workId.observe(this) {
            observeWork(it)
        }
    }

    /**
     * Start export tracks.
     */
    private fun startWork() {
        //Start worker
        workManager.enqueueUniqueWork(
            WORK_TAG,
            ExistingWorkPolicy.KEEP,
            viewModel.createExportWork()
        )
    }

    /**
     * Observe the export work status.
     */
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

    /**
     * Setting up start destination.
     * @param [authStart] define start fragment.
     */
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
                        //hide appbar
                        dataBinding.mainAppBar.setExpanded(false, false)
                    }
                    else -> {
                        //show appbar
                        dataBinding.exFabMain.show()
                        dataBinding.mainAppBar.setExpanded(true, true)
                    }
                }
            }
    }

    /**
     * Start Spotify OAuth.
     */
    private fun requestToken() {
        startActivityForResult(authIntent, AUTH_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (AUTH_REQUEST_CODE == requestCode) {
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

    /**
     * End session for the current user and show [AuthenticationFragment].
     */
    override fun logout() {
        uiRouter.navigateToAuthenticationFragment()
    }
}
