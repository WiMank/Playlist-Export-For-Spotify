package com.wimank.pbfs.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.navigation.findNavController
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.wimank.pbfs.R
import com.wimank.pbfs.databinding.ActivityMainBinding
import com.wimank.pbfs.ui.fragment.AuthenticationFragment
import com.wimank.pbfs.ui.fragment.PlaylistFragment
import com.wimank.pbfs.ui.fragment.UserProfileDialog
import com.wimank.pbfs.ui.utils.UiRouter
import com.wimank.pbfs.util.*
import com.wimank.pbfs.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(),
    PlaylistFragment.BackupFragmentCallback,
    AuthenticationFragment.AuthenticationFragmentCallBack,
    UserProfileDialog.UserProfileDialogCallback {

    private val uiRouter: UiRouter by lazy { UiRouter(findNavController(R.id.main_nav_host)) }

    @VisibleForTesting
    val viewModel: MainActivityViewModel by viewModels()

    private val workManager: WorkManager by lazy { WorkManager.getInstance(this) }

    @Inject
    lateinit var connectivityWatcher: ConnectivityWatcher

    override fun iniView(savedInstanceState: Bundle?) {
        setSupportActionBar(dataBinding.mainToolbar)
        initViewExFab()
        startObserve()
        connectivityWatcher()
        observeWork()

        savedInstanceState?.let {
            dataBinding.exFabMain.changeExFabVisibility(it.getInt(EX_FAB_KEY, View.GONE))
        }
    }

    override fun getLayoutRes() = R.layout.activity_main

    override fun setBindingViewModel() {
        dataBinding.viewModel = viewModel
    }

    private fun initViewExFab() {
        dataBinding.exFabMain.apply {
            setOnClickListener {
                if (dataBinding.exFabMain.isExtended)
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

    /**
     * Start export tracks.
     */
    private fun startWork() {
        //cancel work if it was enqueued
        workManager.cancelUniqueWork(WORK_TAG)
        //start new work
        workManager.enqueueUniqueWork(
            WORK_TAG,
            ExistingWorkPolicy.KEEP,
            viewModel.createExportWork()
        )
    }

    /**
     * Observe the export work status.
     */
    private fun observeWork() {
        workManager.getWorkInfosForUniqueWorkLiveData(WORK_TAG).observe(this) {
            if (it.isNotEmpty()) {
                when (it.last()?.state) {
                    WorkInfo.State.ENQUEUED -> {
                        showSnackBar(dataBinding.exFabMain, R.string.export_planned)
                    }
                    WorkInfo.State.RUNNING -> {
                        dataBinding.exFabMain.startExFabLoadState()
                    }
                    WorkInfo.State.SUCCEEDED -> {
                        dataBinding.exFabMain.stopExFabLoadState()
                        showSnackBarFileShare(dataBinding.exFabMain)
                        workManager.pruneWork()
                    }
                    WorkInfo.State.FAILED -> {
                        dataBinding.exFabMain.stopExFabLoadState()
                        showSnackBar(dataBinding.exFabMain, R.string.export_error)
                        workManager.pruneWork()
                    }
                    WorkInfo.State.CANCELLED -> {
                        dataBinding.exFabMain.stopExFabLoadState()
                        showSnackBar(dataBinding.exFabMain, R.string.export_cancelled)
                        workManager.pruneWork()
                    }
                    WorkInfo.State.BLOCKED -> {
                        showSnackBar(dataBinding.exFabMain, R.string.export_conditions)
                    }
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

    override fun completeAuthentication() {
        uiRouter.navigateToPlaylistFragment()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(EX_FAB_KEY, dataBinding.exFabMain.visibility)
        super.onSaveInstanceState(outState)
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
