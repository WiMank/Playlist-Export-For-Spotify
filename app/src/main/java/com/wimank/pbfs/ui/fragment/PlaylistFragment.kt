package com.wimank.pbfs.ui.fragment

import android.content.Context
import android.os.Bundle
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
            backupFragmentCallback?.stopLoad(it.isNotEmpty())
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
                backupFragmentCallback?.startLoad()
            } else {
                dataBinding.playlistRv.scroll(true)
            }
        }
    }

    private fun observeSnackBarMessages() {
        viewModel.event.observe(this) { event ->
            event.getContentIfNotHandled()?.let {
                when (it) {
                    is Timeout -> {
                        Snackbar.make(
                            dataBinding.playlistsCl,
                            getString(R.string.update_timeout_message, it.time),
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                    is LoadComplete -> {
                        showSnackBar(dataBinding.playlistsCl, it.message)
                    }
                    is LoadError -> {
                        showSnackBar(dataBinding.playlistsCl, it.message)
                        rvAdapter.setData(rvAdapter.showError())
                        backupFragmentCallback?.stopLoad(false)
                    }
                    is OfflineMode -> {
                        showSnackBar(dataBinding.playlistsCl, it.message)
                    }
                    is EmptyList -> {
                        showSnackBar(dataBinding.playlistsCl, it.message)
                    }
                }
            }
        }
    }

    interface BackupFragmentCallback {
        fun startLoad()
        fun stopLoad(listIsNotEmpty: Boolean)
    }
}
