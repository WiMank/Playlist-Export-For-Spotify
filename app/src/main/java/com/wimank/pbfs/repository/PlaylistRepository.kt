package com.wimank.pbfs.repository

import com.wimank.pbfs.domain.model.Playlist
import com.wimank.pbfs.rest.response.NetworkPlaylists
import kotlinx.coroutines.flow.Flow

interface PlaylistRepository {

    suspend fun loadNetworkPlaylists(
        token: String,
        limit: Int = 50,
        offset: Int = 0
    ): NetworkPlaylists

    suspend fun loadLocalPlaylists(): Flow<List<Playlist>>

}
