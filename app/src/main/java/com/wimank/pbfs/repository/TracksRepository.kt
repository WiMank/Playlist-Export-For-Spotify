package com.wimank.pbfs.repository

import com.wimank.pbfs.domain.model.Track
import com.wimank.pbfs.util.START_OFFSET
import com.wimank.pbfs.util.TRACKS_LIMIT

interface TracksRepository {

    suspend fun loadNetworkTracks(
        token: String,
        limit: Int = TRACKS_LIMIT,
        offset: Int = START_OFFSET
    )

    suspend fun loadLocalTracks(playlistId: String): List<Track>

}
