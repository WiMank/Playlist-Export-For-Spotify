package com.wimank.pbfs.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wimank.pbfs.repository.PlaylistRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlaylistViewModel @Inject constructor(private val playlistRepository: PlaylistRepository) :
    ViewModel() {


    init {
        viewModelScope.launch {
            // Timber.i("START !!!!!!!!!!")
            // playlistRepository.startLoadPlaylists()
        }
    }

}
