package com.wimank.pbfs.utils

import com.wimank.pbfs.domain.model.Track
import com.wimank.pbfs.rest.response.NetworkTracks
import com.wimank.pbfs.room.entity.TracksEntity

object MockTracksRepository {

    fun getNetworkTracks(): NetworkTracks {
        return NetworkTracks(
            href = "https://api.spotify.com/v1/users/spotify_espa%C3%B1a/playlists/21THa8j9TaSGuXYNBU5tsC/tracks",
            items = listOf(),
            limit = 100,
            next = null,
            offset = 0,
            previous = null,
            total = 58
        )
    }

    fun getTracksEntities(): List<TracksEntity> {
        return listOf(
            TracksEntity(
                trackId = "0",
                playlistId = "0",
                playlistName = "0",
                url = "0",
                name = "0",
                artists = "0",
                image = "0"
            ),
            TracksEntity(
                trackId = "1",
                playlistId = "1",
                playlistName = "1",
                url = "1",
                name = "1",
                artists = "1",
                image = "1"
            )
        )
    }

    fun getTracks(): List<Track> {
        return listOf(
            Track(
                id = "0", url = "0", name = "0", artists = "0", image = "0", playlistName = "0"

            ),
            Track(
                id = "1", url = "1", name = "1", artists = "1", image = "1", playlistName = "1"
            )
        )
    }
}
