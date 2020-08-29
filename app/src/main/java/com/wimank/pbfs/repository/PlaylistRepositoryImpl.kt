package com.wimank.pbfs.repository

import com.wimank.pbfs.domain.datasource.playlist.PlaylistsLocalDataSource
import com.wimank.pbfs.domain.datasource.playlist.PlaylistsRemoteDataSource
import javax.inject.Inject

class PlaylistRepositoryImpl @Inject constructor(
    private val playlistsLocalDataSource: PlaylistsLocalDataSource,
    private val playlistsRemoteDataSource: PlaylistsRemoteDataSource
) {


    suspend fun startLoadPlaylists() {
        //playlistsRemoteDataSource.loadPlaylists(appPreferencesManager.getToken())
    }

}
