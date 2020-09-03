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
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class PlaylistViewModel @ViewModelInject constructor(
    private val playlistManager: PlaylistManager,
    private val networkManager: NetworkManager
) : ViewModel() {

    private var updateTime = 0L
    val playListData = MutableLiveData<List<Playlist>>()
    val updateData = MutableLiveData(false)

    init {
        //checkConditionsForRequest()
        loadLocalPlaylists()
    }

    fun refreshData() {
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
                showRefresh(false)
            }
        } else {
            loadLocalPlaylists()
        }
    }

    private fun startLoadPlaylists() {
        viewModelScope.launch(context = Dispatchers.IO) {
            try {
                showRefresh(true)
                playlistManager.loadNetworkPlaylists().collect {
                    playListData.postValue(it)
                    showRefresh(false)
                }
            } catch (ex: Exception) {
                clearUpdateTime()
                Timber.e(ex)
            } finally {
                showRefresh(false)
            }
        }
    }

    private fun loadLocalPlaylists() {
        viewModelScope.launch(context = Dispatchers.IO) {
            try {
                showRefresh(true)
                delay(5000)
                playListData.postValue(playlistManager.loadLocalPlaylists())
            } catch (ex: Exception) {
                Timber.e(ex)
            } finally {
                showRefresh(false)
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

    private fun showRefresh(show: Boolean) {
        updateData.postValue(show)
    }

}
