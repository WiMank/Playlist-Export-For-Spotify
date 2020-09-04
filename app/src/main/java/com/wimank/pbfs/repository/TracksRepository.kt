package com.wimank.pbfs.repository

import com.wimank.pbfs.domain.model.Track

interface TracksRepository {

    suspend fun loadLocalTracks(): List<Track>

}
