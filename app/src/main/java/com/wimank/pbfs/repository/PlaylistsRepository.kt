package com.wimank.pbfs.repository

import com.wimank.pbfs.domain.model.Playlist
import com.wimank.pbfs.util.PLAYLISTS_LIMIT
import com.wimank.pbfs.util.START_OFFSET
import kotlinx.coroutines.flow.Flow

interface PlaylistsRepository {

    suspend fun loadNetworkPlaylists(
        token: String,
        limit: Int = PLAYLISTS_LIMIT,
        offset: Int = START_OFFSET
    )

    suspend fun flowPlaylists(): Flow<List<Playlist>>

    suspend fun loadLocalPlaylists(): List<Playlist>

}
