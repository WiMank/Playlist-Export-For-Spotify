package com.wimank.pbfs.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.wimank.pbfs.R
import com.wimank.pbfs.databinding.PlaylistFragmentBinding
import com.wimank.pbfs.ui.adapter.PlaylistAdapter
import com.wimank.pbfs.util.scroll
import com.wimank.pbfs.viewmodel.PlaylistViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlaylistFragment : BaseFragment<PlaylistFragmentBinding>() {

    private val viewModel: PlaylistViewModel by viewModels()
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

        viewModel.playListData.observe(this) {
            rvAdapter.setData(it)
        }
    }

    private fun initSwipeRefresh() {
        dataBinding.playlistSwipeRl.setColorSchemeResources(
            R.color.spotify_green,
            R.color.spotify_black
        )

        viewModel.updateData.observe(this) {
            dataBinding.playlistSwipeRl.isRefreshing = it
            if (it) {
                rvAdapter.setData(rvAdapter.startLoading())
                dataBinding.playlistRv.scroll(false)
            } else {
                dataBinding.playlistRv.scroll(true)
            }
        }
    }

    private fun observeSnackBarMessages() {
        viewModel.updateTimeoutSnackBar.observe(this) {
            it.getContentIfNotHandled()?.let { content ->
                showSnackBar(getString(R.string.update_timeout_message, content))
            }
        }

        viewModel.errorLoadPlaylists.observe(this) {
            it.getContentIfNotHandled()?.let {
                showSnackBar(getString(R.string.loading_error_occurred))
                rvAdapter.setData(rvAdapter.showError())
            }
        }
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(
            dataBinding.playlistsCl,
            message,
            Snackbar.LENGTH_SHORT
        ).show()
    }

    interface BackupFragmentCallback

}
