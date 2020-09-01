package com.wimank.pbfs.domain.usecase

import com.wimank.pbfs.repository.PlaylistRepository
import javax.inject.Inject

class PlaylistManager @Inject constructor(
    private val sessionManager: SessionManager,
    private val playlistRepository: PlaylistRepository
) {

    suspend fun loadPlaylists() {
        sessionManager.checkSessionBeforeRequest().run {
            if (isNotEmpty()) {
                // playlistRepository.loadNetworkPlaylists(this.bearer())
            } else {

            }
        }
    }
}
