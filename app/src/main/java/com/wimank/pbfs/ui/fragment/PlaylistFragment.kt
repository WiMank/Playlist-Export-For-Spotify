package com.wimank.pbfs.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.wimank.pbfs.R
import com.wimank.pbfs.databinding.PlaylistFragmentBinding
import com.wimank.pbfs.ui.adapter.PlaylistAdapter
import com.wimank.pbfs.util.*
import com.wimank.pbfs.viewmodel.PlaylistViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlaylistFragment : BaseFragment<PlaylistFragmentBinding>() {

    @VisibleForTesting
    val viewModel: PlaylistViewModel by viewModels()
    private var backupFragmentCallback: BackupFragmentCallback? = null
    private var rvAdapter: PlaylistAdapter = PlaylistAdapter()

    override fun getLayoutRes() = R.layout.playlist_fragment

    override fun iniView(savedInstanceState: Bundle?) {
        initRecyclerView()
        initSwipeRefresh()
        observeSnackBarMessages()
    }

    override fun setBindingViewModel() {
        dataBinding.viewModel = viewModel
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BackupFragmentCallback)
            backupFragmentCallback = context
    }

    private fun initRecyclerView() {
        dataBinding.playlistRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = rvAdapter
        }

        //observe data
        viewModel.playListData.observe(this) {
            rvAdapter.setData(it)
            //hide or show exFabMain in MainActivity
            backupFragmentCallback?.stopLoad(it.isNotEmpty())
        }
    }

    /**
     * Set up SwipeRefreshLayout and observe update state.
     */
    private fun initSwipeRefresh() {
        dataBinding.playlistSwipeRl.setColorSchemeResources(
            R.color.spotify_green,
            R.color.spotify_black
        )

        viewModel.updateData.observe(this) {
            if (it) {
                rvAdapter.setData(rvAdapter.startLoading())
                backupFragmentCallback?.startLoad()
            }
        }
    }

    /**
     * Observe events from [PlaylistViewModel].
     */
    private fun observeSnackBarMessages() {
        viewModel.event.observe(this) { event ->
            event.getContentIfNotHandled()?.let {
                when (it) {
                    is Timeout -> {
                        showTimeoutSnackBar(it.time)
                    }
                    is LoadComplete -> {
                        showSnackBar(dataBinding.root, it.message)
                    }
                    is LoadError -> {
                        showSnackBar(dataBinding.root, it.message)
                        showError()
                    }
                    is OfflineMode -> {
                        showSnackBar(dataBinding.root, it.message)
                    }
                    is EmptyList -> {
                        showSnackBar(dataBinding.root, it.message)
                    }
                    is Logout -> {
                        showSnackBar(dataBinding.root, it.message)
                        showError()
                    }
                }
            }
        }
    }

    private fun showError() {
        rvAdapter.setData(rvAdapter.showError())
        backupFragmentCallback?.stopLoad(false)
    }

    /**
     * Message about the frequency of data updates.
     */
    private fun showTimeoutSnackBar(time: String) {
        Snackbar.make(
            dataBinding.root,
            getString(R.string.update_timeout_message, time),
            Snackbar.LENGTH_SHORT
        ).show()
    }

    /**
     * [PlaylistFragment] callback.
     */
    interface BackupFragmentCallback {
        /**
         * Start loading playlists.
         */
        fun startLoad()

        /**
         * Stop loading playlists.
         * @param [listIsNotEmpty] reports on the availability of data.
         */
        fun stopLoad(listIsNotEmpty: Boolean)
    }
}
