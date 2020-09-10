package com.wimank.pbfs.repository

import com.wimank.pbfs.domain.model.Playlist
import com.wimank.pbfs.util.PLAYLISTS_LIMIT
import com.wimank.pbfs.util.START_OFFSET
import kotlinx.coroutines.flow.Flow

/**
 * Loads playlists from web or database.
 */
interface PlaylistsRepository {

    /**
     * Loads playlists from web.
     */
    suspend fun loadNetworkPlaylists(
        token: String,
        limit: Int = PLAYLISTS_LIMIT,
        offset: Int = START_OFFSET
    )

    /**
     * Asynchronous data stream for playlists.
     */
    suspend fun flowPlaylists(): Flow<List<Playlist>>

    /**
     * Loads playlists from database.
     */
    suspend fun loadLocalPlaylists(): List<Playlist>

}
