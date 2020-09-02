package com.wimank.pbfs.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wimank.pbfs.domain.usecase.PlaylistManager
import com.wimank.pbfs.util.NetworkManager
import com.wimank.pbfs.util.ONE_MINUTE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class PlaylistViewModel @ViewModelInject constructor(
    private val playlistManager: PlaylistManager,
    private val networkManager: NetworkManager
) : ViewModel() {

    init {
        startLoadPlaylists()
    }

    private fun startLoadPlaylists() {
        try {
            if (canUpdate()) {
                if (networkManager.isNetworkAvailable()) {
                    viewModelScope.launch(context = Dispatchers.IO) {
                        playlistManager.loadNetworkPlaylists()
                    }
                } else {
                    //TODO: загрузить локальные данные
                }
            } else {
                //TODO: показать снэкбар о частоте обновления
            }
        } catch (ex: Exception) {
            Timber.e(ex)
        }
    }

    private fun canUpdate(): Boolean {
        val updateTime = System.currentTimeMillis() + ONE_MINUTE
        return updateTime <= System.currentTimeMillis()
    }
}
