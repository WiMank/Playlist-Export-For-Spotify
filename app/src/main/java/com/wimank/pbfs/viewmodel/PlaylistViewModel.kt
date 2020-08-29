package com.wimank.pbfs.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wimank.pbfs.repository.PlaylistRepositoryImpl
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlaylistViewModel @Inject constructor(private val playlistRepositoryImpl: PlaylistRepositoryImpl) :
    ViewModel() {


    init {
        viewModelScope.launch {
            // Timber.i("START !!!!!!!!!!")
            // playlistRepository.startLoadPlaylists()
        }
    }

}
