package com.wimank.pbfs.domain.usecase

import com.wimank.pbfs.domain.model.Playlist
import com.wimank.pbfs.repository.PlaylistsRepository
import com.wimank.pbfs.util.AccessTokenException
import com.wimank.pbfs.util.bearer
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlaylistManager @Inject constructor(
    private val sessionManager: SessionManager,
    private val playlistsRepository: PlaylistsRepository
) {

    suspend fun loadNetworkPlaylists(): Flow<List<Playlist>> {
        sessionManager.checkSessionBeforeRequest().run {
            if (isNotEmpty()) {
                playlistsRepository.loadNetworkPlaylists(this.bearer())
            } else {
                throw AccessTokenException()
            }
        }
        return playlistsRepository.flowPlaylists()
    }

    suspend fun loadLocalPlaylists(): List<Playlist> {
        return playlistsRepository.loadLocalPlaylists()
    }
}
