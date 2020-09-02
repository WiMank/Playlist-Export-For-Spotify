package com.wimank.pbfs.repository

import com.wimank.pbfs.domain.model.Playlist
import kotlinx.coroutines.flow.Flow

interface PlaylistRepository {

    suspend fun loadNetworkPlaylists(
        token: String,
        limit: Int = 50,
        offset: Int = 0
    ): Flow<List<Playlist>>

    suspend fun loadLocalPlaylists(): List<Playlist>

}
