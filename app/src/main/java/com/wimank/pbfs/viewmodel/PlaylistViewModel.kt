package com.wimank.pbfs.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wimank.pbfs.domain.model.Playlist
import com.wimank.pbfs.domain.usecase.PlaylistManager
import com.wimank.pbfs.util.Event
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
    val updateData = MutableLiveData(false)
    val updateTimeoutSnackBar = MutableLiveData<Event<String>>()
    val errorLoadPlaylists = MutableLiveData<Event<Unit>>()

    init {
        checkConditionsForRequest()
    }

    fun refreshData() {
        checkConditionsForRequest()
    }

    private fun checkConditionsForRequest() {
        if (networkManager.isNetworkAvailable()) {
            if (canUpdate()) {
                startLoadPlaylists()
                newUpdateTime()
            } else {
                updateTimeoutSnackBar.value = Event(currentTimeOutTime())
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
                errorLoadPlaylists.postValue(Event(Unit))
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
                playListData.postValue(playlistManager.loadLocalPlaylists())
            } catch (ex: Exception) {
                errorLoadPlaylists.postValue(Event(Unit))
                Timber.e(ex)
            } finally {
                showRefresh(false)
            }
        }
    }

    private fun canUpdate(): Boolean {
        if (updateTime == 0L) {
            return true
        }
        return updateTime <= System.currentTimeMillis()
    }

    private fun newUpdateTime() {
        updateTime = System.currentTimeMillis() + ONE_MINUTE
    }

    private fun clearUpdateTime() {
        updateTime = 0L
    }

    private fun currentTimeOutTime() = ((updateTime - System.currentTimeMillis()) / 1000).toString()

    private fun showRefresh(show: Boolean) {
        updateData.postValue(show)
    }
}
