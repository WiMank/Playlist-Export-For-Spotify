package com.wimank.pbfs.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.wimank.pbfs.repository.PlaylistRepositoryImpl

class PlaylistViewModel @ViewModelInject constructor(private val playlistRepositoryImpl: PlaylistRepositoryImpl) :
    ViewModel()
