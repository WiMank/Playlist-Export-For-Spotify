package com.wimank.pbfs.domain.usecase

import com.wimank.pbfs.domain.model.Playlist
import com.wimank.pbfs.repository.PlaylistRepository
import com.wimank.pbfs.util.AccessTokenException
import com.wimank.pbfs.util.bearer
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlaylistManager @Inject constructor(
    private val sessionManager: SessionManager,
    private val playlistRepository: PlaylistRepository
) {

    suspend fun loadNetworkPlaylists(): Flow<List<Playlist>> {
        sessionManager.checkSessionBeforeRequest().run {
            if (isNotEmpty()) {
                return playlistRepository.loadNetworkPlaylists(this.bearer())
            } else {
                throw AccessTokenException()
            }
        }
    }
}
