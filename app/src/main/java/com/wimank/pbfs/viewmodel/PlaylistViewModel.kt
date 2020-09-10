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
import retrofit2.HttpException
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

    /**
     * Checking the conditions for a request.
     */
    private fun checkConditionsForRequest() {
        //checking for network availability
        if (networkManager.isNetworkAvailable()) {
            //checking the elapsed time after the last request
            if (canUpdate()) {
                startLoadPlaylists()
                newUpdateTime()
            } else {
                //show snackbar with the remaining time
                event.value = Event(Timeout(currentTimeOutTime()))
                showRefresh(false)
            }
        } else {
            //offline mode
            loadLocalPlaylists()
            event.value = Event(OfflineMode((R.string.no_internet_connection)))
        }
    }

    /**
     * Start load playlists.
     */
    private fun startLoadPlaylists() {
        viewModelScope.launch(context = Dispatchers.IO) {
            try {
                showRefresh(true)

                //wait data
                playlistManager.loadNetworkPlaylists().collect {
                    //set data
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
            } catch (httpEx: HttpException) {
                if (httpEx.code() == UNAUTHORIZED_CODE)
                    event.postValue(Event(Logout(R.string.need_authentication)))
            } finally {
                showRefresh(false)
            }
        }
    }

    /**
     * Loading playlists from the database.
     */
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

    /**
     * Checking whether data can be updated.
     */
    private fun canUpdate(): Boolean {
        if (updateTime == 0L) {
            return true
        }
        return updateTime <= System.currentTimeMillis()
    }

    /**
     * New time for update request.
     */
    private fun newUpdateTime() {
        updateTime = System.currentTimeMillis() + ONE_MINUTE
    }

    /**
     * Clear update time.
     */
    private fun clearUpdateTime() {
        updateTime = 0L
    }

    /**
     * @return time until data update.
     */
    private fun currentTimeOutTime() = ((updateTime - System.currentTimeMillis()) / 1000).toString()

    /**
     * To show or hide the animation updates.
     */
    private fun showRefresh(show: Boolean) {
        updateData.postValue(show)
    }
}
