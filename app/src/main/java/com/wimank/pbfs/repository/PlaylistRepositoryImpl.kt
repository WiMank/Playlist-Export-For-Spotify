package com.wimank.pbfs.repository

import com.wimank.pbfs.domain.model.Playlist
import com.wimank.pbfs.mapper.PlaylistMapper
import com.wimank.pbfs.rest.PlaylistsApi
import com.wimank.pbfs.rest.response.NetworkPlaylists
import com.wimank.pbfs.room.dao.PlaylistDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PlaylistRepositoryImpl @Inject constructor(
    private val playlistDao: PlaylistDao,
    private val playlistsApi: PlaylistsApi
) : PlaylistRepository {

    //TODO: Загрузка по страницам
    override suspend fun loadNetworkPlaylists(
        token: String,
        limit: Int,
        offset: Int
    ): NetworkPlaylists {
        return playlistsApi.loadPlaylistsList(token, limit, offset)
    }

    override suspend fun loadLocalPlaylists(): Flow<List<Playlist>> {
        val playlistMapper = PlaylistMapper()
        return playlistDao.getAllPlaylists().map { list ->
            list.map {
                playlistMapper.map(it)
            }
        }
    }
}
