package com.wimank.pbfs.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.wimank.pbfs.domain.usecase.PlaylistManager

class PlaylistViewModel @ViewModelInject constructor(
    private val playlistManager: PlaylistManager
) : ViewModel()
