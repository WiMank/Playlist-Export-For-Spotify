package com.wimank.pbfs.repository

import com.wimank.pbfs.domain.model.Track
import kotlinx.coroutines.flow.Flow

interface TracksRepository {

    suspend fun loadNetworkTracks(token: String, limit: Int, offset: Int)

    suspend fun loadLocalTracks(): List<Track>

    suspend fun flowTracks(): Flow<List<Track>>

}
