package com.wimank.pbfs.mapper

import com.wimank.pbfs.domain.model.Track
import com.wimank.pbfs.rest.response.NetworkTracks
import com.wimank.pbfs.room.entity.TracksEntity
import com.wimank.pbfs.util.EMPTY_STRING
import javax.inject.Inject

class NetworkTracksMapper @Inject constructor() {

    fun map(
        playlistName: String,
        playlistId: String,
        input: List<NetworkTracks>
    ): List<TracksEntity> {
        val resultList = mutableListOf<TracksEntity>()
        input.forEach {
            it.items.map { item ->
                resultList.add(
                    TracksEntity(
                        trackId = item.track.id,
                        playlistId = playlistId,
                        playlistName = playlistName,
                        url = item.track.href,
                        name = item.track.name,
                        artists =
                        item.track.artists.joinToString(separator = ",") { joinItem -> joinItem.name },
                        image = if (item.track.album.images.isNullOrEmpty())
                            EMPTY_STRING else item.track.album.images[0].url
                    )
                )
            }
        }
        return resultList
    }
}

class TracksMapper @Inject constructor() : Mapper<TracksEntity, Track> {
    override fun map(input: TracksEntity): Track {
        return Track(
            id = input.trackId,
            url = input.url,
            name = input.name,
            artists = input.artists,
            image = input.image,
            playlistName = input.playlistName
        )
    }
}
