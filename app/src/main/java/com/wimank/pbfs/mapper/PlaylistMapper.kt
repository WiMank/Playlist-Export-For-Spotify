package com.wimank.pbfs.mapper

import com.wimank.pbfs.domain.model.Playlist
import com.wimank.pbfs.rest.response.NetworkPlaylists
import com.wimank.pbfs.room.entity.PlaylistsEntity
import com.wimank.pbfs.util.EMPTY_STRING
import javax.inject.Inject

class PlaylistMapper @Inject constructor() : Mapper<PlaylistsEntity, Playlist> {
    override fun map(input: PlaylistsEntity): Playlist {
        return Playlist(
            name = input.playlistName,
            image = input.playlistImage,
            tracksCount = input.tracksCount,
            id = input.playlistId,
            isCollaborative = input.isCollaborative,
            isPublic = input.isPublic
        )
    }
}

class NetworkPlaylistMapper @Inject constructor() :
    Mapper<List<NetworkPlaylists>, List<PlaylistsEntity>> {
    override fun map(input: List<NetworkPlaylists>): List<PlaylistsEntity> {
        val resultList = mutableListOf<PlaylistsEntity>()
        input.forEach {
            it.items.forEach { items ->
                resultList.add(
                    PlaylistsEntity(
                        playlistId = items.id,
                        playlistName = items.name,
                        tracksCount = items.tracks?.total ?: 0,
                        playlistImage =
                        if (items.images.isNullOrEmpty()) EMPTY_STRING else items.images[0].url
                            ?: EMPTY_STRING,
                        tracksUrl = items.tracks?.href ?: EMPTY_STRING,
                        isPublic = items.isPublic ?: false,
                        isCollaborative = items.collaborative
                    )
                )
            }
        }
        return resultList
    }
}
