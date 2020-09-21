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
     * Asynchronous data stream for playlists.
     */
    suspend fun flowingPlaylists(): Flow<List<Playlist>>

    /**
     * Loads playlists from database.
     */
    suspend fun loadLocalPlaylists(): List<Playlist>

    /**
     * Loading from network and save new playlists to database.
     */
    suspend fun refreshData(token: String, limit: Int = PLAYLISTS_LIMIT, offset: Int = START_OFFSET)

}
