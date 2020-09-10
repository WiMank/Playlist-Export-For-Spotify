package com.wimank.pbfs.repository

import com.wimank.pbfs.domain.model.Playlist
import com.wimank.pbfs.mapper.NetworkPlaylistMapper
import com.wimank.pbfs.mapper.PlaylistMapper
import com.wimank.pbfs.rest.PlaylistsApi
import com.wimank.pbfs.rest.response.NetworkPlaylists
import com.wimank.pbfs.room.dao.PlaylistDao
import com.wimank.pbfs.room.entity.PlaylistsEntity
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PlaylistsRepositoryImpl @Inject constructor(
    private val playlistDao: PlaylistDao,
    private val playlistsApi: PlaylistsApi,
    private val playlistMapper: PlaylistMapper,
    private val networkPlaylistMapper: NetworkPlaylistMapper
) : PlaylistsRepository {

    //All playlist saved here
    private val listNetworkPlaylists = mutableListOf<NetworkPlaylists>()

    override suspend fun loadNetworkPlaylists(token: String, limit: Int, offset: Int) {
        playlistsApi.loadPlaylists(token, limit, offset).run {
            listNetworkPlaylists.add(this)
            if (next != null) {
                loadNext(token, next)
            }
        }
        saveResponse(networkPlaylistMapper.map(listNetworkPlaylists))
    }

    override suspend fun flowPlaylists() = playlistDao.flowPlaylists().map { list ->
        list.map {
            playlistMapper.map(it)
        }
    }

    override suspend fun loadLocalPlaylists(): List<Playlist> {
        return playlistDao.getPlaylists().map {
            playlistMapper.map(it)
        }
    }

    /**
     * Recursive method to load all playlists.
     */
    private suspend fun loadNext(token: String, nextUrl: String): NetworkPlaylists {
        val pageResp = playlistsApi.loadNextPlaylists(token, nextUrl)
        //If next url not null, load next page
        if (pageResp.next != null) {
            listNetworkPlaylists.add(loadNext(token, pageResp.next))
            return pageResp
        }
        listNetworkPlaylists.add(pageResp)
        return pageResp
    }

    /**
     * Save new playlists to database
     */
    private suspend fun saveResponse(playlistsEntityList: List<PlaylistsEntity>) {
        //Clear and save new playlists
        playlistDao.clearAndInsertPlaylists(playlistsEntityList)
        listNetworkPlaylists.clear()
    }
}
