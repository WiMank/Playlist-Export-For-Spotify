package com.wimank.pbfs.domain.datasource.playlist

import com.wimank.pbfs.rest.PlaylistsApi
import javax.inject.Inject

class PlaylistsRemoteDataSource @Inject constructor(private val playlistsApi: PlaylistsApi) {

    suspend fun loadPlaylists(token: String, limit: String, offset: String) =
        playlistsApi.loadPlaylistsList(token, limit, offset)

}
