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

    private var updateTime = 0L

    init {
        checkConditionsForRequest()
    }

    private fun checkConditionsForRequest() {
        if (networkManager.isNetworkAvailable()) {
            if (canUpdate(updateTime)) {
                startLoadPlaylists()
                newUpdateTime()
            } else {
                //TODO: показать снэкбар о частоте обновления
            }
        } else {
            //TODO: офлайн режим
        }
    }

    private fun startLoadPlaylists() {
        viewModelScope.launch(context = Dispatchers.IO) {
            try {
                playlistManager.loadNetworkPlaylists()
            } catch (ex: Exception) {
                clearUpdateTime()
                Timber.e(ex)
            }
        }
    }

    private fun canUpdate(updateTime: Long = 0L): Boolean {
        return updateTime <= System.currentTimeMillis()
    }

    private fun newUpdateTime() {
        updateTime = System.currentTimeMillis() + ONE_MINUTE
    }

    private fun clearUpdateTime() {
        updateTime = 0L
    }
}
