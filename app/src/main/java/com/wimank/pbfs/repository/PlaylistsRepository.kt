package com.wimank.pbfs.repository

import com.wimank.pbfs.domain.model.Playlist
import kotlinx.coroutines.flow.Flow

interface PlaylistsRepository {

    suspend fun loadNetworkPlaylists(
        token: String,
        limit: Int = 50,
        offset: Int = 0
    )

    suspend fun flowPlaylists(): Flow<List<Playlist>>

    suspend fun loadLocalPlaylists(): List<Playlist>

}
