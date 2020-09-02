package com.wimank.pbfs.repository

import arrow.core.extensions.list.monad.map
import com.wimank.pbfs.domain.model.Playlist
import com.wimank.pbfs.mapper.NetworkPlaylistMapper
import com.wimank.pbfs.mapper.PlaylistMapper
import com.wimank.pbfs.rest.PlaylistsApi
import com.wimank.pbfs.room.dao.PlaylistDao
import com.wimank.pbfs.room.entity.PlaylistsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PlaylistRepositoryImpl @Inject constructor(
    private val playlistDao: PlaylistDao,
    private val playlistsApi: PlaylistsApi,
    private val playlistMapper: PlaylistMapper,
    private val networkPlaylistMapper: NetworkPlaylistMapper
) : PlaylistRepository {

    //TODO: Загрузка по страницам
    override suspend fun loadNetworkPlaylists(
        token: String,
        limit: Int,
        offset: Int
    ): Flow<List<Playlist>> {

        saveResponse(
            networkPlaylistMapper.map(
                playlistsApi.loadPlaylistsList(
                    token,
                    limit,
                    offset
                )
            )
        )

        return playlistDao.flowPlaylists().map { list ->
            list.map {
                playlistMapper.map(it)
            }
        }
    }

    private fun saveResponse(playlistsEntityList: List<PlaylistsEntity>) {
        playlistDao.insert(playlistsEntityList)
    }

    override suspend fun loadLocalPlaylists(): List<Playlist> {
        return playlistDao.getPlaylists().map {
            playlistMapper.map(it)
        }
    }
}
