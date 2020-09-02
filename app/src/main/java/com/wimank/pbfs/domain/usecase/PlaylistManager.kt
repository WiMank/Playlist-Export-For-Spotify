package com.wimank.pbfs.domain.usecase

import com.wimank.pbfs.repository.PlaylistRepository
import com.wimank.pbfs.util.bearer
import javax.inject.Inject

class PlaylistManager @Inject constructor(
    private val sessionManager: SessionManager,
    private val playlistRepository: PlaylistRepository
) {

    suspend fun loadNetworkPlaylists() {
        sessionManager.checkSessionBeforeRequest().run {
            if (isNotEmpty()) {
                playlistRepository.loadNetworkPlaylists(this.bearer())
            } else {

            }
        }
    }

    fun loadLocalPlaylists() {

    }

}
