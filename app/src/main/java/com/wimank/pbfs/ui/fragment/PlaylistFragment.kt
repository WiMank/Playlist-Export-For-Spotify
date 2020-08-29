package com.wimank.pbfs.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.viewModels
import com.wimank.pbfs.R
import com.wimank.pbfs.databinding.PlaylistFragmentBinding
import com.wimank.pbfs.util.AppPreferencesManager
import com.wimank.pbfs.viewmodel.PlaylistViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PlaylistFragment : BaseFragment<PlaylistFragmentBinding>() {

    @Inject
    lateinit var appPreferencesManager: AppPreferencesManager
    private val viewModel: PlaylistViewModel by viewModels { viewModelFactory }
    private var backupFragmentCallback: BackupFragmentCallback? = null

    override fun getLayoutRes() = R.layout.playlist_fragment

    override fun iniView(savedInstanceState: Bundle?) {

    }

    override fun setBindingViewModel() {
        dataBinding.viewModel = viewModel
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BackupFragmentCallback)
            backupFragmentCallback = context
    }

    interface BackupFragmentCallback

}
