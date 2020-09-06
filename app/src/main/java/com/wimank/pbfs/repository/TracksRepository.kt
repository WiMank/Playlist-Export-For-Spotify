package com.wimank.pbfs.repository

import com.wimank.pbfs.domain.model.Track

interface TracksRepository {

    suspend fun loadNetworkTracks(token: String, limit: Int = 100, offset: Int = 0)

    suspend fun loadLocalTracks(): List<Track>

}
