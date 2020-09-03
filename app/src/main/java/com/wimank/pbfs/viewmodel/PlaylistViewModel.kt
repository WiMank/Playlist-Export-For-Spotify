package com.wimank.pbfs.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wimank.pbfs.domain.model.Playlist
import com.wimank.pbfs.domain.usecase.PlaylistManager
import com.wimank.pbfs.util.NetworkManager
import com.wimank.pbfs.util.ONE_MINUTE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class PlaylistViewModel @ViewModelInject constructor(
    private val playlistManager: PlaylistManager,
    private val networkManager: NetworkManager
) : ViewModel() {

    private var updateTime = 0L
    val playListData = MutableLiveData<List<Playlist>>()


    init {
        //checkConditionsForRequest()
        loadLocalPlaylists()
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
                playlistManager.loadNetworkPlaylists().collect {
                    playListData.postValue(it)
                }
            } catch (ex: Exception) {
                clearUpdateTime()
                Timber.e(ex)
            }
        }
    }

    private fun loadLocalPlaylists() {
        viewModelScope.launch(context = Dispatchers.IO) {
            playListData.postValue(playlistManager.loadLocalPlaylists())
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
