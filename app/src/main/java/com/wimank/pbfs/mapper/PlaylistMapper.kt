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
            id = input.playlistId,
            isCollaborative = input.isCollaborative,
            isPublic = input.isCollaborative
        )
    }
}

class NetworkPlaylistMapper @Inject constructor() :
    Mapper<List<NetworkPlaylists>, List<PlaylistsEntity>> {
    override fun map(input: List<NetworkPlaylists>): List<PlaylistsEntity> {
        val resultList = mutableListOf<PlaylistsEntity>()
        val k = input.forEach {
            it.items.forEach { items ->
                resultList.add(
                    PlaylistsEntity(
                        playlistId = items.id,
                        playlistName = items.name,
                        playlistImage = items.images?.get(0)?.url ?: EMPTY_STRING,
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
