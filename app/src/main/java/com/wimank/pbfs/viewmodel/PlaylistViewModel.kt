package com.wimank.pbfs.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wimank.pbfs.R
import com.wimank.pbfs.domain.model.Playlist
import com.wimank.pbfs.domain.usecase.PlaylistManager
import com.wimank.pbfs.util.*
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
    val emptyData = MutableLiveData(false)
    val event = MutableLiveData<Event<EventMessage>>()

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
                event.value = Event(Timeout(currentTimeOutTime()))
                showRefresh(false)
            }
        } else {
            loadLocalPlaylists()
            event.value = Event(OfflineMode((R.string.no_internet_connection)))
        }
    }

    private fun startLoadPlaylists() {
        viewModelScope.launch(context = Dispatchers.IO) {
            try {
                showRefresh(true)
                playlistManager.loadNetworkPlaylists().collect {
                    playListData.postValue(it)
                    if (it.isNotEmpty()) {
                        event.postValue(Event(LoadComplete(R.string.load_playlists_complete)))
                    } else {
                        event.postValue(Event(EmptyList(R.string.playlists_empty)))
                        emptyData.postValue(true)
                    }
                    showRefresh(false)
                }
            } catch (ex: Exception) {
                event.postValue(Event(LoadError(R.string.load_playlists_error)))
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
                playListData.postValue(playlistManager.loadLocalPlaylists())
            } catch (ex: Exception) {
                event.postValue(Event(LoadError(R.string.load_playlists_error)))
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
