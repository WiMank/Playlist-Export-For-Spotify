package com.wimank.pbfs.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wimank.pbfs.domain.usecase.PlaylistManager
import com.wimank.pbfs.util.NetworkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class PlaylistViewModel @ViewModelInject constructor(
    private val playlistManager: PlaylistManager,
    private val networkManager: NetworkManager
) : ViewModel() {

    init {
        viewModelScope.launch(context = Dispatchers.IO) {
            playlistManager.loadPlaylists()
            Timber.i("===LOAD PLAYLISTS===")
        }
    }

}
