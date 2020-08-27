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
    private val viewModel: PlaylistViewModel by viewModels()
    private var backupFragmentCallback: BackupFragmentCallback? = null

    override fun getLayoutRes() = R.layout.playlist_fragment

    override fun iniView(savedInstanceState: Bundle?) {
        appPreferencesManager.getToken().ifEmpty {
            //TODO: Показывать кнопку для начала процесса аутентификации
            backupFragmentCallback?.requestAuthentication()
        }
    }

    override fun setBindingViewModel() {

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BackupFragmentCallback)
            backupFragmentCallback = context
    }

    interface BackupFragmentCallback {
        fun requestAuthentication()
    }

}
