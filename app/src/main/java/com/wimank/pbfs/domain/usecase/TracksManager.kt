package com.wimank.pbfs.domain.usecase

import com.wimank.pbfs.domain.model.Track
import com.wimank.pbfs.repository.TracksRepository
import com.wimank.pbfs.util.AccessTokenException
import com.wimank.pbfs.util.bearer
import javax.inject.Inject

class TracksManager @Inject constructor(
    private val sessionManager: SessionManager,
    private val tracksRepository: TracksRepository
) {

    suspend fun loadNetworkTracks() {
        sessionManager.checkSessionBeforeRequest().run {
            if (isNotEmpty()) {
                tracksRepository.loadNetworkTracks(this.bearer())
            } else {
                throw AccessTokenException()
            }
        }
    }

    suspend fun loadLocalTracks(playlistId: String): List<Track> {
        return tracksRepository.loadLocalTracks(playlistId)
    }
}
