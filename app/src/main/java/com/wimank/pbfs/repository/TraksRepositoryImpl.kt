package com.wimank.pbfs.repository

import com.wimank.pbfs.domain.model.Track
import com.wimank.pbfs.mapper.NetworkTracksMapper
import com.wimank.pbfs.mapper.TracksMapper
import com.wimank.pbfs.rest.TracksApi
import com.wimank.pbfs.rest.response.NetworkTracks
import com.wimank.pbfs.room.dao.PlaylistDao
import com.wimank.pbfs.room.dao.TracksDao
import com.wimank.pbfs.room.entity.TracksEntity
import javax.inject.Inject

class TracksRepositoryImpl @Inject constructor(
    private val tracksApi: TracksApi,
    private val tracksDao: TracksDao,
    private val playlistDao: PlaylistDao,
    private val networkTracksMapper: NetworkTracksMapper,
    private val tracksMapper: TracksMapper
) : TracksRepository {

    private val listNetworkTracks = mutableListOf<NetworkTracks>()

    override suspend fun loadNetworkTracks(token: String, limit: Int, offset: Int) {
        playlistDao.getPlaylists().forEach { entity ->
            tracksApi.loadTracks(token, entity.tracksUrl).run {
                listNetworkTracks.add(this)
                if (next != null) {
                    loadNext(token, next)
                }
            }
            saveResponse(networkTracksMapper.map(entity.playlistId, listNetworkTracks))
        }
    }

    override suspend fun loadLocalTracks(): List<Track> {
        return tracksDao.getTracks().map {
            tracksMapper.map(it)
        }
    }

    private suspend fun loadNext(token: String, nextUrl: String): NetworkTracks {
        val pageResp = tracksApi.loadTracks(token, nextUrl)
        if (pageResp.next != null) {
            listNetworkTracks.add(loadNext(token, pageResp.next))
            return pageResp
        }
        listNetworkTracks.add(pageResp)
        return pageResp
    }

    private suspend fun saveResponse(tracks: List<TracksEntity>) {
        tracksDao.clearAndInsertTracks(tracks)
        listNetworkTracks.clear()
    }
}
