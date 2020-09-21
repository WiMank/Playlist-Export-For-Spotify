package com.wimank.pbfs.domain.usecase

import com.wimank.pbfs.domain.model.Playlist
import com.wimank.pbfs.repository.PlaylistsRepository
import com.wimank.pbfs.util.AccessTokenException
import com.wimank.pbfs.util.bearer
import javax.inject.Inject

class PlaylistManager @Inject constructor(
    private val sessionManager: SessionManager,
    private val playlistsRepository: PlaylistsRepository
) {

    suspend fun loadNetworkPlaylists() = run {
        with(sessionManager.checkSessionBeforeRequest()) {
            if (isNotEmpty()) {
                playlistsRepository.refreshData(this.bearer())
                playlistsRepository.flowingPlaylists()
            } else {
                throw AccessTokenException()
            }
        }
    }

    suspend fun loadLocalPlaylists(): List<Playlist> {
        return playlistsRepository.loadLocalPlaylists()
    }
}
