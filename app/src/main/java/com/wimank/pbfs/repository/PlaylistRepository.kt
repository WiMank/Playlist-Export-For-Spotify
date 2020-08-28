package com.wimank.pbfs.repository

import com.wimank.pbfs.domain.datasource.playlist.PlaylistsLocalDataSource
import com.wimank.pbfs.domain.datasource.playlist.PlaylistsRemoteDataSource
import com.wimank.pbfs.util.AppPreferencesManager
import javax.inject.Inject

class PlaylistRepository @Inject constructor(
    private val playlistsLocalDataSource: PlaylistsLocalDataSource,
    private val playlistsRemoteDataSource: PlaylistsRemoteDataSource,
    private val appPreferencesManager: AppPreferencesManager
) {


    suspend fun startLoadPlaylists() {
        playlistsRemoteDataSource.loadPlaylists(appPreferencesManager.getToken())
    }

}
